package com.hasib.mylangsheet.data.room.entites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class Category(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("category_id") val categoryId: Int = 0,
    @ColumnInfo("category_name") val categoryName: String,
)
