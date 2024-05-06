package com.hasib.mylangsheet.ui.screens.words.wordmain

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hasib.mylangsheet.data.Repository.LangRepository
import com.hasib.mylangsheet.data.room.entites.category.Category
import com.hasib.mylangsheet.data.room.entites.word.Word
import com.hasib.mylangsheet.ui.screens.categories.catdialog.CatDialogViewModel
import com.hasib.mylangsheet.ui.screens.words.actions.DbAction
import com.hasib.mylangsheet.ui.screens.words.dialog.DialogViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class WordUiState(
    var searchQuery: String = "",
    val isSearchActive: Boolean = false,
)

@HiltViewModel
class WordViewModel @Inject constructor(
    private val repository: LangRepository
) : ViewModel() {

    val dialogViewModel = DialogViewModel()
    private val dialogUiState = dialogViewModel.dialogUiState

    init {
        getAllWords()
        getCategoryWords(dialogUiState.value.categoryName)
    }


    val catDialogViewModel = CatDialogViewModel()

    val topbarDropDownState = mutableStateOf(false)

    val dbAction = mutableStateOf(DbAction.NO_ACTION)


    private val _wordUiState = MutableStateFlow(WordUiState())
    val wordUiState: StateFlow<WordUiState>
        get() = _wordUiState

    fun updateWordState(
        searchQuery: String = wordUiState.value.searchQuery,
        isSearchActive: Boolean = wordUiState.value.isSearchActive
    ) {
        _wordUiState.update {
            it.copy(
                searchQuery = searchQuery,
                isSearchActive = isSearchActive
            )
        }
    }

    fun resetWordState() {
        _wordUiState.update {
            it.copy(
                searchQuery = "",
                isSearchActive = false
            )
        }
    }


    @RequiresApi(Build.VERSION_CODES.N)
    fun handleDatabase() {
        when (dbAction.value) {
            DbAction.INSERT -> insertWord(category = dialogUiState.value.categoryName)
            DbAction.UPDATE -> updateWord()
            DbAction.MANUAL_UPDATE -> updateWordManual()
            DbAction.DELETE -> deleteWord()
            DbAction.SEARCH -> searchDatabase(query = wordUiState.value.searchQuery)
            DbAction.NO_ACTION -> TODO()
        }
    }

    // Word crud operations
    private fun insertWord(category: String) {
        viewModelScope.launch {
            val newWord = dialogUiState.value.selectedWord.copy(category = category)
            repository.insertWord(newWord)
        }
    }
    // Retrieve all words operation


    val allWords = MutableStateFlow<List<Word>>(emptyList())
    fun getAllWords() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllWords.collectLatest {
                allWords.value = it
            }
        }
    }


    private var _categoryWords = MutableStateFlow<List<Word>>(emptyList())
    val categoryWords: StateFlow<List<Word>>
        get() = _categoryWords

    fun getCategoryWords(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCategoryWithWords(category).collectLatest { words ->
                _categoryWords.value = words[0].words
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

    @RequiresApi(Build.VERSION_CODES.N)
    fun searchDatabase(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.searchDatabase(query).collectLatest {
                allWords.value = it.filter { word ->
                    word.word.contains(query, ignoreCase = true)
                }
            }
        }
    }


    fun insertCategory() {
        viewModelScope.launch(Dispatchers.IO) {
            val newCategory =
                Category(categoryName = catDialogViewModel.catDialogUiState.value.newCategoryText)
            repository.insertCategory(newCategory)
        }
    }

}