package com.hasib.mylangsheet.data.room.migrations

import androidx.room.RenameColumn
import androidx.room.migration.AutoMigrationSpec
import com.hasib.mylangsheet.util.Constants

@RenameColumn.Entries(
    RenameColumn(
        tableName = Constants.WORD_TABLE_NAME,
        fromColumnName = "id",
        toColumnName = "word_id"
    ),

    RenameColumn(
        tableName = Constants.WORD_TABLE_NAME,
        fromColumnName = "wordMeaning",
        toColumnName = "word_meaning"
    )
)
class Migration1to2 : AutoMigrationSpec