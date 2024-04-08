package com.hasib.mylangsheet.ui.screens.words.dialog

data class DialogUiState(
    var wordTextFieldValue: String = "",
    var meaningTextFieldValue: String = "",
    var isDialogOpen: Boolean = false,
    var isSimpleDialogOpen: Boolean = false,
    var currentWordId: Int = 0
)
