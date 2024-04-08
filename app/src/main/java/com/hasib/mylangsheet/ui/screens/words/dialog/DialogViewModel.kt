package com.hasib.mylangsheet.ui.screens.words.dialog


import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class DialogViewModel : ViewModel() {

    init {
        Log.d("TAG", "dialog viewmodel loaded: ")
    }

    //TODO: DIALOG OPERATIONS
    private val _dialogUiState = MutableStateFlow(DialogUiState())
    val dialogUiState: StateFlow<DialogUiState>
        get() = _dialogUiState

    fun isDialogOpen() {
        _dialogUiState.update {
            it.copy(isDialogOpen = !it.isDialogOpen)
        }
    }

    fun isSimpleDialogOpen() {
        _dialogUiState.update {
            it.copy(isSimpleDialogOpen = !it.isSimpleDialogOpen)
        }
    }

    fun updateDialogState(
        wordTextFieldValue: String = dialogUiState.value.wordTextFieldValue,
        meaningTextFieldValue: String = dialogUiState.value.meaningTextFieldValue,
        isDialogOpen: Boolean = dialogUiState.value.isDialogOpen,
        isSimpleDialogOpen: Boolean = dialogUiState.value.isSimpleDialogOpen,
        currentWordId: Int = dialogUiState.value.currentWordId
    ) {
        _dialogUiState.update { currentState ->
            currentState.copy(
                wordTextFieldValue = wordTextFieldValue,
                meaningTextFieldValue = meaningTextFieldValue,
                isDialogOpen = isDialogOpen,
                isSimpleDialogOpen = isSimpleDialogOpen,
                currentWordId = currentWordId
            )
        }
    }

    fun resetDialogState() {
        _dialogUiState.update { currentState ->
            currentState.copy(
                wordTextFieldValue = "",
                meaningTextFieldValue = "",
                isDialogOpen = false
            )
        }
    }


    //TODO: CRUD OPERATIONS
//    fun insertWord() {
//        viewModelScope.launch {
//            val newWord = Word(
//                word = _dialogUiState.value.wordTextFieldValue,
//                wordMeaning = _dialogUiState.value.meaningTextFieldValue,
//            )
//            repository.insertWord(newWord)
//        }
//    }
//
//    private var _wordsList = MutableStateFlow<List<Word>>(emptyList())
//    val wordList: StateFlow<List<Word>>
//        get() = _wordsList
//
//    fun getAllWords() {
//        viewModelScope.launch {
//            repository.getAllWords.collectLatest {
//                _wordsList.value = it
//            }
//        }
//    }

}