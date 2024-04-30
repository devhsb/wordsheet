package com.hasib.mylangsheet.data.room

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameColumn
import androidx.room.RenameTable
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.hasib.mylangsheet.data.room.entites.Category
import com.hasib.mylangsheet.data.room.entites.Word
import com.hasib.mylangsheet.data.room.entites.relations.CategoryWordCrossRef
import com.hasib.mylangsheet.data.room.migrations.Migration1to2
import com.hasib.mylangsheet.data.room.migrations.Migration3to4
import com.hasib.mylangsheet.util.Constants.DATABASE_VERSION
import com.hasib.mylangsheet.util.Constants.WORD_TABLE_NAME

@Database(
    entities = [
        Word::class,
        Category::class,
        CategoryWordCrossRef::class
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
        )
    ]
)
abstract class LangDatabase : RoomDatabase() {

    abstract fun getLangDao(): LangDao


}