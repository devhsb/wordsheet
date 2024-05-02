package com.hasib.mylangsheet.data.room.entites.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.hasib.mylangsheet.data.room.entites.category.Category
import com.hasib.mylangsheet.data.room.entites.word.Word

data class WordWithCategory(
    @Embedded
    val word: Word,

    @Relation(
        parentColumn = "word",
        entityColumn = "category_name",
    )
    val categories: List<Category>
)