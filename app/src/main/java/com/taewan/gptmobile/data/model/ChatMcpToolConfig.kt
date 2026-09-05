package com.taewan.gptmobile.data.model

import kotlinx.serialization.Serializable

/**
 * Represents per-chat MCP tool selection configuration.
 */
@Serializable
data class ChatMcpToolConfig(
    val chatId: Long,
    val enabledToolIds: Set<String> = emptySet(),
    val enableAllToolsByDefault: Boolean = true
) {
    fun isToolEnabled(toolId: String): Boolean {
        return if (enableAllToolsByDefault && enabledToolIds.isEmpty()) {
            true
        } else {
            enabledToolIds.contains(toolId)
        }
    }

    fun toggleTool(toolId: String): ChatMcpToolConfig {
        val next = enabledToolIds.toMutableSet()
        if (next.contains(toolId)) {
            next.remove(toolId)
        } else {
            next.add(toolId)
        }
        return copy(
            enabledToolIds = next,
            enableAllToolsByDefault = false
        )
    }
}

/**
 * Metadata for a tool that can be toggled per chat.
 */
@Serializable
data class AvailableChatTool(
    val id: String,
    val name: String,
    val description: String,
    val category: String,
    val isNative: Boolean = false
)
