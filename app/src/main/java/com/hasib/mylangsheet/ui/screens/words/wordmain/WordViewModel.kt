package com.hasib.mylangsheet.ui.screens.words.wordmain

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hasib.mylangsheet.data.Repository.LangRepository
import com.hasib.mylangsheet.data.room.entites.Category
import com.hasib.mylangsheet.data.room.entites.Word
import com.hasib.mylangsheet.ui.screens.categories.catdialog.CatDialogViewModel
import com.hasib.mylangsheet.ui.screens.words.dialog.DialogViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordViewModel @Inject constructor(
    private val repository: LangRepository
) : ViewModel() {

    init {
        getAllWords()
    }

    val dialogViewModel = DialogViewModel()
    private val dialogUiState = dialogViewModel.dialogUiState

    val catDialogViewModel = CatDialogViewModel()

    val topbarDropDownState = mutableStateOf(false)
    val action = mutableStateOf(Action.NO_ACTION)

    fun handleDatabase() {
        when (action.value) {
            Action.INSERT -> insertWord()
            Action.UPDATE -> updateWord()
            Action.MANUAL_UPDATE -> updateWordManual()
            Action.DELETE -> deleteWord()
            Action.NO_ACTION -> TODO()
        }
    }

    // Word crud operations
    private fun insertWord() {
        viewModelScope.launch {
            val newWord = dialogUiState.value.selectedWord
            repository.insertWord(newWord)
        }
    }

    // Retrieve all words operation
    private var _wordsList = MutableStateFlow<List<Word>>(emptyList())
    val wordList: StateFlow<List<Word>>
        get() = _wordsList

    private fun getAllWords() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllWords.collectLatest {
                _wordsList.value = it
            }
        }
    }

    // Delete operation
    private fun deleteWord() {
        viewModelScope.launch(Dispatchers.IO) {
            val selectedWord = dialogUiState.value.selectedWord
            repository.deleteWord(selectedWord)
        }
    }

    // Update operation
    private fun updateWord() {
        viewModelScope.launch(Dispatchers.IO) {
            val updatedWord = dialogUiState.value.selectedWord
            repository.updateWord(updatedWord)
        }
    }

    // Manual update
    private fun updateWordManual() {
        viewModelScope.launch(Dispatchers.IO) {
            val word = dialogUiState.value.selectedWord.word
            val wordMeaning = dialogUiState.value.selectedWord.wordMeaning
            repository.updateWord(word, wordMeaning)
        }
    }

    //TODO: Category crud operations
    fun insertCategory() {
        viewModelScope.launch(Dispatchers.IO) {
            val newCategory =
                Category(categoryName = catDialogViewModel.catDialogUiState.value.newCategoryText)
            repository.insertCategory(newCategory)
        }
    }


}