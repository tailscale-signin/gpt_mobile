package com.taewan.gptmobile.service

import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object AgentCoordinator {
    private val _isAgentRunning = MutableStateFlow(false)
    val isAgentRunning: StateFlow<Boolean> = _isAgentRunning.asStateFlow()

    private val _currentTaskTitle = MutableStateFlow<String?>(null)
    val currentTaskTitle: StateFlow<String?> = _currentTaskTitle.asStateFlow()

    fun startTask(context: Context, taskTitle: String = "Executing Autonomous Agent Task...") {
        _isAgentRunning.value = true
        _currentTaskTitle.value = taskTitle
        AgentForegroundService.startService(context, taskTitle)
    }

    fun finishTask(context: Context) {
        _isAgentRunning.value = false
        _currentTaskTitle.value = null
        AgentForegroundService.stopService(context)
    }
}
