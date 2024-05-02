package com.hasib.mylangsheet.data.room.migrations

import androidx.room.DeleteColumn
import androidx.room.DeleteTable
import androidx.room.migration.AutoMigrationSpec

@DeleteTable(
    tableName = "CategoryWordCrossRef"
)

class Migration4to5 : AutoMigrationSpec