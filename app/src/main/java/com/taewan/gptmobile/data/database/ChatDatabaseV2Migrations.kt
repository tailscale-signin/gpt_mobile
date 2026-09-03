package com.taewan.gptmobile.data.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_10_11 = object : Migration(10, 11) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE chats_v2 ADD COLUMN is_favorite INTEGER NOT NULL DEFAULT 0")
    }
}
