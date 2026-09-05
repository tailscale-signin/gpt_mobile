package com.taewan.gptmobile.tool.location

import android.content.Context
import org.json.JSONObject

class DeviceLocationTool(private val context: Context) {

    private val provider = DeviceLocationProvider(context)

    companion object {
        const val TOOL_NAME = "get_device_location"
        const val TOOL_DESCRIPTION = "Retrieve the current physical location coordinates (latitude, longitude, accuracy) of the user's Android device."

        fun getToolDefinition(): Map<String, Any> {
            return mapOf(
                "name" to TOOL_NAME,
                "description" to TOOL_DESCRIPTION,
                "parameters" to mapOf(
                    "type" to "object",
                    "properties" to emptyMap<String, Any>()
                )
            )
        }
    }

    suspend fun execute(): String {
        val result = provider.getCurrentLocation()
        val json = JSONObject()
        json.put("success", result.success)
        if (result.success) {
            json.put("latitude", result.latitude)
            json.put("longitude", result.longitude)
            json.put("accuracy_meters", result.accuracy)
            json.put("altitude_meters", result.altitude)
            json.put("provider", result.provider)
            json.put("timestamp", result.timestamp)
        } else {
            json.put("error", result.error ?: "Unknown error obtaining location")
        }
        return json.toString()
    }
}
