package com.hasib.mylangsheet.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.hasib.mylangsheet.data.model.Word
import kotlinx.coroutines.flow.Flow
import com.hasib.mylangsheet.util.Constants.WORD_TABLE_NAME

@Dao
interface LangDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWord(word: Word)

    @Update
    suspend fun updateWord(word: Word)

    @Delete
    suspend fun deleteWord(word: Word)

    @Query("SELECT * FROM $WORD_TABLE_NAME")
    fun getAllWords() : Flow<List<Word>>

    @Query("SELECT * FROM $WORD_TABLE_NAME WHERE id = :id")
    fun getSelectedWord(id: Int) : Flow<Word>
}