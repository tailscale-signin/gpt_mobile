package com.taewan.gptmobile.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.taewan.gptmobile.data.database.entity.ChatRoomV2Entity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatRoomV2Dao {
    @Query("SELECT * FROM chats_v2 ORDER BY is_favorite DESC, updated_at DESC")
    fun getAllChatsFlow(): Flow<List<ChatRoomV2Entity>>

    @Query("SELECT * FROM chats_v2 ORDER BY is_favorite DESC, updated_at DESC")
    suspend fun getAllChats(): List<ChatRoomV2Entity>

    @Query("SELECT * FROM chats_v2 WHERE id = :chatId")
    suspend fun getChatById(chatId: String): ChatRoomV2Entity?

    @Query("UPDATE chats_v2 SET is_favorite = :isFavorite WHERE id = :chatId")
    suspend fun updateFavorite(chatId: String, isFavorite: Boolean)

    @Update
    suspend fun updateChat(chat: ChatRoomV2Entity)
}
