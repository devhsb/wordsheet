package com.hasib.mylangsheet.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.hasib.mylangsheet.data.room.entites.Category
import com.hasib.mylangsheet.data.room.entites.Word
import com.hasib.mylangsheet.data.room.entites.relations.CategoryWithWord
import com.hasib.mylangsheet.data.room.entites.relations.WordWithCategory
import kotlinx.coroutines.flow.Flow
import com.hasib.mylangsheet.util.Constants.WORD_TABLE_NAME

@Dao
interface LangDao {

    //word operations
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWord(word: Word)

    @Update
    suspend fun updateWord(word: Word)

    @Delete
    suspend fun deleteWord(word: Word)

    @Query("SELECT * FROM $WORD_TABLE_NAME")
    fun getAllWords() : Flow<List<Word>>

    @Query("SELECT * FROM $WORD_TABLE_NAME WHERE word_id = :wordId")
    fun getSelectedWord(wordId: Int) : Flow<Word>


    //category operations
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategory(category: Category)

    @Delete
    suspend fun deleteCategory(category: Category)

    @Query("SELECT * FROM category")
    fun getCategories()  : Flow<List<Category>>

    @Transaction
    @Query("SELECT * FROM Category WHERE category_name = :categoryName")
    fun getCategoriesWithWords(categoryName: String) : Flow<List<CategoryWithWord>>

    @Transaction
    @Query("SELECT * FROM $WORD_TABLE_NAME WHERE word = :word")
    fun getWordsWithCategories(word: String) : Flow<List<WordWithCategory>>

}