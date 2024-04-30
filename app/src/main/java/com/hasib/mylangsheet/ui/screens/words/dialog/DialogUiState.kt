package com.hasib.mylangsheet.ui.screens.words.dialog

import com.hasib.mylangsheet.data.room.entites.Word

data class DialogUiState(
    var selectedWord: Word = Word("", ""),
    var isDialogOpen: Boolean = false,
    var isSimpleDialogOpen: Boolean = false,
)
