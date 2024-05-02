package com.hasib.mylangsheet.data.room.entites.word

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hasib.mylangsheet.util.Constants.WORD_TABLE_NAME

@Entity(tableName = WORD_TABLE_NAME)
data class Word(

    @PrimaryKey(autoGenerate = false)
    val word: String,

    @ColumnInfo("word_meaning") val wordMeaning: String,

    @ColumnInfo(defaultValue = "general") val category: String = "general"
)