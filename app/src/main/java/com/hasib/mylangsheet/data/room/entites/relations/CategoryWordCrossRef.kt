package com.hasib.mylangsheet.data.room.entites.relations

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["category_id", "word_id"])
data class CategoryWordCrossRef(
    @ColumnInfo("category_id") val categoryId: Int,
    @ColumnInfo("word_id") val id: Int
)
