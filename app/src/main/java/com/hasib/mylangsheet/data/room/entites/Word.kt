package com.hasib.mylangsheet.data.room.entites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hasib.mylangsheet.util.Constants.WORD_TABLE_NAME

@Entity(tableName = WORD_TABLE_NAME)
data class Word(
    @PrimaryKey(autoGenerate = true) @ColumnInfo("word_id") val id: Int = 0,

    val word: String,

    @ColumnInfo("word_meaning") val wordMeaning: String
)