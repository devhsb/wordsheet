package com.hasib.mylangsheet.ui.screens.categories.catdialog

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CatDialogViewModel: ViewModel() {

    private val _catDialogUiStatte = MutableStateFlow(CatDialogUiState())
    val catDialogUiState: StateFlow<CatDialogUiState>
        get() = _catDialogUiStatte

    fun updateCatDialogState(
        isCatDialogOpen: Boolean = catDialogUiState.value.isCatDialogOpen,
        newCategoryText: String = catDialogUiState.value.newCategoryText
    ) {
        _catDialogUiStatte.update {
            it.copy(
                isCatDialogOpen = isCatDialogOpen,
                newCategoryText = newCategoryText
            )
        }
    }
    fun resetCatDialogState() {
        _catDialogUiStatte.update {
            it.copy(
                isCatDialogOpen = false,
                newCategoryText = ""
            )
        }
    }
    fun isDialogOpen(dialogState: Boolean = catDialogUiState.value.isCatDialogOpen) {
        _catDialogUiStatte.update {
            it.copy(
                isCatDialogOpen = !dialogState
            )
        }
    }

}