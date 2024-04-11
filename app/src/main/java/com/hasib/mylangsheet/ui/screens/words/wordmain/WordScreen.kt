package com.hasib.mylangsheet.ui.screens.words.wordmain

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun WordsScreen(
    wordViewModel: WordViewModel,
    onWordItemClicked: () -> Unit,
    onPracticeItemClicked: () -> Unit
) {
//    val wordViewModel: WordViewModel = viewModel()
    WordScreenContent(
        wordViewModel = wordViewModel,
        onWordItemClicked = onWordItemClicked,
        onPracticeItemClicked = onPracticeItemClicked
    )
}