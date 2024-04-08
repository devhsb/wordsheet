package com.hasib.mylangsheet.data.Repository

import com.hasib.mylangsheet.data.model.Word
import com.hasib.mylangsheet.data.room.LangDao
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class LangRepository @Inject constructor(private val langDao: LangDao) {

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

    suspend fun deleteWord(word: Word) {
        langDao.deleteWord(word)
    }
}