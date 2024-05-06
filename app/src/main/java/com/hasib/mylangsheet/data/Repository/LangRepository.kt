package com.hasib.mylangsheet.data.Repository

import com.hasib.mylangsheet.data.room.entites.word.Word
import com.hasib.mylangsheet.data.room.LangDao
import com.hasib.mylangsheet.data.room.entites.category.Category
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class LangRepository @Inject constructor(private val langDao: LangDao) {

    //word operations
    val getAllWords = langDao.getAllWords()

    fun getSelectedWord(id: Int): Flow<Word> {
        return langDao.getSelectedWord(id)
    }

    suspend fun insertWord(word: Word) {
        langDao.insertWord(word)
    }

    suspend fun updateWord(word: Word) {
        langDao.updateWord(word)
    }

    suspend fun updateWord(word: String, wordMeaning: String) {
        langDao.updateWord(word, wordMeaning)
    }

    suspend fun deleteWord(word: Word) {
        langDao.deleteWord(word)
    }

    fun searchDatabase(searchQuery: String): Flow<List<Word>> {
        return langDao.searchDatabase(searchQuery)
    }

    //category operations

    val getCategories = langDao.getCategories()
    val getCategoryWithWords = fun(category: String) = langDao.getCategoryWithWords(category)

    suspend fun insertCategory(category: Category) {
        langDao.insertCategory(category)
    }
    suspend fun deleteCategory(category: Category) {
        langDao.deleteCategory(category)
    }




















}