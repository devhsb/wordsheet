package com.hasib.mylangsheet.data.room

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.hasib.mylangsheet.data.room.entites.category.Category
import com.hasib.mylangsheet.data.room.entites.word.Word
import com.hasib.mylangsheet.data.room.migrations.Migration1to2
import com.hasib.mylangsheet.data.room.migrations.Migration3to4
import com.hasib.mylangsheet.data.room.migrations.Migration4to5
import com.hasib.mylangsheet.util.Constants.DATABASE_VERSION

@Database(
    entities = [
        Word::class,
        Category::class,
    ],
    version = DATABASE_VERSION,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(
            from = 1, to = 2,
            spec = Migration1to2::class
        ),

        AutoMigration(
            from = 3, to = 4,
            spec = Migration3to4::class
        ),

        AutoMigration(
            from = 4, to = 5,
            spec = Migration4to5::class
        )
    ]
)
abstract class LangDatabase : RoomDatabase() {

    abstract fun getLangDao(): LangDao


}