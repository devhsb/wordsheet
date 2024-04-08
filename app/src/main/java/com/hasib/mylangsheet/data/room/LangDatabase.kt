package com.hasib.mylangsheet.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hasib.mylangsheet.data.model.Word
import com.hasib.mylangsheet.util.Constants.DATABASE_VERSION

@Database(entities = [Word::class], version = DATABASE_VERSION)
abstract class LangDatabase : RoomDatabase() {
    abstract fun getLangDao(): LangDao
}