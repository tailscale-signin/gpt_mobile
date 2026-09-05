package com.taewan.gptmobile.tool.location

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import androidx.core.content.ContextCompat
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

data class DeviceLocationResult(
    val success: Boolean,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val accuracy: Float? = null,
    val altitude: Double? = null,
    val provider: String? = null,
    val timestamp: Long? = null,
    val error: String? = null
)

class DeviceLocationProvider(private val context: Context) {

    private val locationManager: LocationManager? =
        context.getSystemService(Context.LOCATION_SERVICE) as? LocationManager

    fun hasLocationPermission(): Boolean {
        val fineLocation = ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val coarseLocation = ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        return fineLocation || coarseLocation
    }

    @SuppressLint("MissingPermission")
    suspend fun getCurrentLocation(): DeviceLocationResult {
        if (!hasLocationPermission()) {
            return DeviceLocationResult(
                success = false,
                error = "Location permission not granted. Please grant ACCESS_FINE_LOCATION or ACCESS_COARSE_LOCATION."
            )
        }

        if (locationManager == null) {
            return DeviceLocationResult(
                success = false,
                error = "LocationManager service not available on this device."
            )
        }

        // Try getting last known location first
        val gpsLocation = try {
            locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        } catch (_: Exception) { null }

        val networkLocation = try {
            locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        } catch (_: Exception) { null }

        val bestLastKnown = when {
            gpsLocation != null && networkLocation != null -> {
                if (gpsLocation.time >= networkLocation.time) gpsLocation else networkLocation
            }
            gpsLocation != null -> gpsLocation
            else -> networkLocation
        }

        if (bestLastKnown != null && (System.currentTimeMillis() - bestLastKnown.time) < 60_000) {
            return mapLocationToResult(bestLastKnown)
        }

        // Request single update
        return suspendCancellableCoroutine { continuation ->
            val listener = object : LocationListener {
                override fun onLocationChanged(location: Location) {
                    locationManager.removeUpdates(this)
                    if (continuation.isActive) {
                        continuation.resume(mapLocationToResult(location))
                    }
                }

                override fun onProviderDisabled(provider: String) {}
                override fun onProviderEnabled(provider: String) {}
                @Deprecated("Deprecated in Java")
                override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
            }

            try {
                val provider = when {
                    locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) -> LocationManager.GPS_PROVIDER
                    locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) -> LocationManager.NETWORK_PROVIDER
                    else -> LocationManager.PASSIVE_PROVIDER
                }

                locationManager.requestSingleUpdate(provider, listener, Looper.getMainLooper())

                continuation.invokeOnCancellation {
                    locationManager.removeUpdates(listener)
                }
            } catch (e: Exception) {
                if (continuation.isActive) {
                    if (bestLastKnown != null) {
                        continuation.resume(mapLocationToResult(bestLastKnown))
                    } else {
                        continuation.resume(
                            DeviceLocationResult(
                                success = false,
                                error = "Failed to request location: ${e.message}"
                            )
                        )
                    }
                }
            }
        }
    }

    private fun mapLocationToResult(location: Location): DeviceLocationResult {
        return DeviceLocationResult(
            success = true,
            latitude = location.latitude,
            longitude = location.longitude,
            accuracy = location.accuracy,
            altitude = location.altitude,
            provider = location.provider,
            timestamp = location.time
        )
    }
}
