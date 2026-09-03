package com.taewan.gptmobile.domain.repository

import com.taewan.gptmobile.data.database.entity.ChatRoomV2Entity
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    fun getAllChatsFlow(): Flow<List<ChatRoomV2Entity>>
    suspend fun getAllChats(): List<ChatRoomV2Entity>
    suspend fun getChatById(chatId: String): ChatRoomV2Entity?
    suspend fun setChatFavoriteV2(chatId: String, isFavorite: Boolean)
    suspend fun searchChatsV2(query: String): List<ChatRoomV2Entity>
}
