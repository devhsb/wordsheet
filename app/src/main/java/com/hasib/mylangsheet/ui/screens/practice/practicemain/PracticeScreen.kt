package com.hasib.mylangsheet.ui.screens.practice.practicemain

import androidx.compose.runtime.Composable
import com.hasib.mylangsheet.ui.screens.words.wordmain.WordViewModel

@Composable
fun PracticeScreen(
    wordViewModel: WordViewModel,
    onWordItemClicked: () -> Unit,
    onPracticeItemClicked: () -> Unit,
    onCategoryItemClicked: () -> Unit
) {
    PracticeContent(
        wordViewModel = wordViewModel,
        onWordItemClicked = onWordItemClicked,
        onPracticeItemClicked = onPracticeItemClicked,
        onCategoryClicked = onCategoryItemClicked
    )
}