package com.hasib.mylangsheet.data.room.entites.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.hasib.mylangsheet.data.room.entites.category.Category
import com.hasib.mylangsheet.data.room.entites.word.Word

data class CategoryWithWord(
    @Embedded
    val category: Category,

    @Relation(
        parentColumn = "category_name",
        entityColumn = "category",
    )
    val words: List<Word>
)