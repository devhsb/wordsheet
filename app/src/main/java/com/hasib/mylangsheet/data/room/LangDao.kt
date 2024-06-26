package com.hasib.mylangsheet.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.hasib.mylangsheet.data.room.entites.category.Category
import com.hasib.mylangsheet.data.room.entites.word.Word
import com.hasib.mylangsheet.data.room.entites.relations.CategoryWithWord
import kotlinx.coroutines.flow.Flow
import com.hasib.mylangsheet.util.Constants.WORD_TABLE_NAME

@Dao
interface LangDao {

    //word operations
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWord(word: Word)


    @Query("UPDATE word_table SET word = :word, word_meaning = :wordMeaning, category = :category WHERE word = :wordId")
    suspend fun updateWord(
        wordId: String,
        word: String, wordMeaning: String, category: String
    )

    @Delete
    suspend fun deleteWord(word: Word)

    @Query("SELECT * FROM $WORD_TABLE_NAME")
    fun getAllWords(): Flow<List<Word>>

    @Query("SELECT * FROM $WORD_TABLE_NAME WHERE word = :wordId")
    fun getSelectedWord(wordId: Int): Flow<Word>

    @Query("SELECT * FROM $WORD_TABLE_NAME WHERE word LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<Word>>


    //category operations
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategory(category: Category)

    @Delete
    suspend fun deleteCategory(category: Category)

    @Query("SELECT * FROM category")
    fun getCategories(): Flow<List<Category>>

    @Transaction
    @Query("SELECT * FROM category WHERE category_name = :category")
    fun getCategoryWithWords(category: String): Flow<List<CategoryWithWord>>

}