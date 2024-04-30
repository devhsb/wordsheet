package com.hasib.mylangsheet.data.room.entites.relations

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["category_name", "word"])
data class CategoryWordCrossRef(
    @ColumnInfo("category_name") val categoryName: String,
    @ColumnInfo("word") val word: String
)
