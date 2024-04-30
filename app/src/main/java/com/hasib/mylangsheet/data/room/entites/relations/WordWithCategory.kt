package com.hasib.mylangsheet.data.room.entites.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.hasib.mylangsheet.data.room.entites.Category
import com.hasib.mylangsheet.data.room.entites.Word

data class WordWithCategory(
    @Embedded
    val word: Word,

    @Relation(
        parentColumn = "word",
        entityColumn = "category_name",
        associateBy = Junction(CategoryWordCrossRef::class)
    )
    val categories: List<Category>
)