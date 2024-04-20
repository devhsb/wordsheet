package com.hasib.mylangsheet.ui.screens.words.wordmain

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hasib.mylangsheet.data.Repository.LangRepository
import com.hasib.mylangsheet.data.model.Word
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
        when(action.value) {
            Action.INSERT -> insertWord()
            Action.UPDATE -> updateWord()
            Action.DELETE -> deleteWord()
            Action.NO_ACTION -> TODO()
        }
    }

    //TODO: Insert operation
    private fun insertWord() {
        viewModelScope.launch {
            val newWord = Word(
                word = dialogUiState.value.wordTextFieldValue,
                wordMeaning = dialogUiState.value.meaningTextFieldValue,
            )
            repository.insertWord(newWord)
        }
    }

    // Retrieve all operation
    private var _wordsList = MutableStateFlow<List<Word>>(emptyList())
    val wordList: StateFlow<List<Word>>
        get() = _wordsList

    private fun getAllWords() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllWords.collectLatest {
                _wordsList.value = it
                Log.d("TAG", "wordlist: ${_wordsList.value} ")
            }
        }
    }

    // Delete operation
    private fun deleteWord() {
        viewModelScope.launch(Dispatchers.IO) {
            val selectedWord = Word(
                id = dialogUiState.value.currentWordId,
                word = dialogUiState.value.wordTextFieldValue,
                wordMeaning = dialogUiState.value.meaningTextFieldValue
            )
            repository.deleteWord(selectedWord)
        }
    }

    // Update operation
    private fun updateWord() {
        viewModelScope.launch(Dispatchers.IO) {
            val updatedWord = Word(
                id = dialogUiState.value.currentWordId,
                word = dialogUiState.value.wordTextFieldValue,
                wordMeaning = dialogUiState.value.meaningTextFieldValue
            )
            repository.updateWord(updatedWord)
        }
    }


}