package com.hasib.mylangsheet.ui.screens.categories.catmain

import androidx.compose.runtime.Composable
import com.hasib.mylangsheet.ui.screens.words.wordmain.WordViewModel

@Composable
fun CategoryScreen(
    wordViewModel: WordViewModel,
    onWordItemClicked: () -> Unit = {},
    onPracticeItemClicked: () -> Unit = {},
    onCategoryClicked: () -> Unit = {},
) {
    CategoryContent(
        onWordItemClicked = onWordItemClicked,
        onPracticeItemClicked = onPracticeItemClicked,
        onCategoryItemClicked = onCategoryClicked,
        wordViewModel = wordViewModel
    )
}