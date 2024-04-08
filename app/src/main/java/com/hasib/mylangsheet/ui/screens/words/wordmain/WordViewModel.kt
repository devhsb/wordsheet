package com.hasib.mylangsheet.ui.screens.words.wordmain

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hasib.mylangsheet.data.Repository.LangRepository
import com.hasib.mylangsheet.data.model.Word
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


    //TODO: Insert operation
    fun insertWord() {
        viewModelScope.launch {
            val newWord = Word(
                word = dialogUiState.value.wordTextFieldValue,
                wordMeaning = dialogUiState.value.meaningTextFieldValue,
            )
            repository.insertWord(newWord)
        }
    }

    //TODO: Retrieve all operation
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

    //TODO: Delete operation
    fun deleteWord() {
        viewModelScope.launch(Dispatchers.IO) {
            val selectedWord = Word(
                id = dialogUiState.value.currentWordId,
                word = dialogUiState.value.wordTextFieldValue,
                wordMeaning = dialogUiState.value.meaningTextFieldValue
            )
            repository.deleteWord(selectedWord)
        }
    }


    fun getSelectedWord(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getSelectedWord(id)
                .collectLatest { selectedWord ->

                }
        }
    }


}