package com.hasib.mylangsheet.data.room.entites.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.hasib.mylangsheet.data.room.entites.Category
import com.hasib.mylangsheet.data.room.entites.Word

data class CategoryWithWord(
    @Embedded
    val category: Category,

    @Relation(
        parentColumn = "category_name",
        entityColumn = "word",
        associateBy = Junction(CategoryWordCrossRef::class)
    )
    val words: List<Word>
)