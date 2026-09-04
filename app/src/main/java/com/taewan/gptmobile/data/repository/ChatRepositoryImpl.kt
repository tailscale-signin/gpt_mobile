package com.taewan.gptmobile.data.repository

import com.taewan.gptmobile.data.database.dao.ChatRoomV2Dao
import com.taewan.gptmobile.data.database.entity.ChatRoomV2Entity
import com.taewan.gptmobile.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow

class ChatRepositoryImpl(
    private val chatRoomV2Dao: ChatRoomV2Dao
) : ChatRepository {

    override fun getAllChatsFlow(): Flow<List<ChatRoomV2Entity>> =
        chatRoomV2Dao.getAllChatsFlow()

    override suspend fun getAllChats(): List<ChatRoomV2Entity> =
        chatRoomV2Dao.getAllChats()

    override suspend fun getChatById(chatId: String): ChatRoomV2Entity? =
        chatRoomV2Dao.getChatById(chatId)

    override suspend fun setChatFavoriteV2(chatId: String, isFavorite: Boolean) {
        chatRoomV2Dao.updateFavorite(chatId, isFavorite)
    }

    override suspend fun searchChatsV2(query: String): List<ChatRoomV2Entity> {
        val allChats = chatRoomV2Dao.getAllChats()
        return allChats
            .filter { it.title.contains(query, ignoreCase = true) }
            .sortedWith(
                compareByDescending<ChatRoomV2Entity> { it.isFavorite }
                    .thenByDescending { it.updatedAt }
            )
    }
}
