package com.hasib.mylangsheet.data.room.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object Migration2to3 : Migration(2, 3) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS category (" +
                    "    category_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "    category_name TEXT NOT NULL" +
                    ")"
        )

        db.execSQL(
            "CREATE TABLE IF NOT EXISTS CategoryWordCrossRef (" +
                    "    category_id INTEGER NOT NULL," +
                    "    word_id INTEGER NOT NULL," +
                    "    PRIMARY KEY (category_id, word_id)" +
                    ")"
        )
    }
}
