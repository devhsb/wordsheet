package com.hasib.mylangsheet.ui.screens.words.dialog

import com.hasib.mylangsheet.data.room.entites.word.Word

data class DialogUiState(
    var selectedWord: Word = Word("", ""),
    var oldWord: String = "",
    var isDialogOpen: Boolean = false,
    var isSimpleDialogOpen: Boolean = false,
    var dialogTitle: String = "Add New Word",
    var categoryName: String = "general"
)
