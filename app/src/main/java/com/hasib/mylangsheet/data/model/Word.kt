package com.hasib.mylangsheet.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hasib.mylangsheet.util.Constants.WORD_TABLE_NAME

@Entity(tableName = WORD_TABLE_NAME)
data class Word(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val word: String,
    val wordMeaning: String
)