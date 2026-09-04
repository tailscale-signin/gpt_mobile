package com.taewan.gptmobile.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.taewan.gptmobile.data.database.dao.ChatRoomV2Dao
import com.taewan.gptmobile.data.database.entity.ChatRoomV2Entity

@Database(entities = [ChatRoomV2Entity::class], version = 11, exportSchema = false)
abstract class ChatDatabase : RoomDatabase() {
    abstract fun chatRoomV2Dao(): ChatRoomV2Dao

    companion object {
        @Volatile
        private var INSTANCE: ChatDatabase? = null

        fun getDatabase(context: Context): ChatDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ChatDatabase::class.java,
                    "chat_database"
                )
                    .addMigrations(MIGRATION_10_11)
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
