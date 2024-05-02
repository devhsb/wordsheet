package com.hasib.mylangsheet.data.room.entites.category

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class Category(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("category_name") val categoryName: String,
)
