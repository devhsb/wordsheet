package com.hasib.mylangsheet.data.room.migrations


import androidx.room.DeleteColumn
import androidx.room.RenameColumn
import androidx.room.migration.AutoMigrationSpec
import com.hasib.mylangsheet.util.Constants.WORD_TABLE_NAME


@DeleteColumn.Entries(
    DeleteColumn(
        tableName = WORD_TABLE_NAME,
        columnName = "word_id"
    ),
    DeleteColumn(
        tableName = "category",
        columnName = "category_id"
    )
)
@RenameColumn.Entries(
    RenameColumn(
        tableName = "CategoryWordCrossRef",
        fromColumnName = "category_id",
        toColumnName = "category_name"
    ),
    RenameColumn(
        tableName = "CategoryWordCrossRef",
        fromColumnName = "word_id",
        toColumnName = "word"
    )
)
class Migration3to4 : AutoMigrationSpec