package com.hasib.mylangsheet.ui.screens.words.dialog


import androidx.lifecycle.ViewModel
import com.hasib.mylangsheet.data.room.entites.word.Word
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class DialogViewModel : ViewModel() {

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
        selectedWord: Word = dialogUiState.value.selectedWord,
        isDialogOpen: Boolean = dialogUiState.value.isDialogOpen,
        isSimpleDialogOpen: Boolean = dialogUiState.value.isSimpleDialogOpen,
        dialogTitle: String = dialogUiState.value.dialogTitle
    ) {
        _dialogUiState.update { currentState ->
            currentState.copy(
                selectedWord = selectedWord,
                isDialogOpen = isDialogOpen,
                isSimpleDialogOpen = isSimpleDialogOpen,
                dialogTitle = dialogTitle
            )
        }
    }

    fun resetDialogState() {
        _dialogUiState.update { currentState ->
            currentState.copy(
                selectedWord = Word("", ""),
                isDialogOpen = false
            )
        }
    }

}