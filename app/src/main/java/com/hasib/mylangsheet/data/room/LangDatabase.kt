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
            spec = LangDatabase.MyAutoMigration::class
        )
    ]
)
abstract class LangDatabase : RoomDatabase() {

    abstract fun getLangDao(): LangDao

    @RenameColumn.Entries(
        RenameColumn(
            tableName = WORD_TABLE_NAME,
            fromColumnName = "id",
            toColumnName = "word_id"
        ),

        RenameColumn(
            tableName = WORD_TABLE_NAME,
            fromColumnName = "wordMeaning",
            toColumnName = "word_meaning"
        )
    )
    class MyAutoMigration : AutoMigrationSpec

    companion object {
        val migration2to3 = object : Migration(2, 3) {
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
    }


}