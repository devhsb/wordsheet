package com.hasib.mylangsheet.ui.screens.categories.catmain

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.hasib.mylangsheet.ui.screens.words.wordmain.WordViewModel

@Composable
fun CategoryScreen(
    wordViewModel: WordViewModel,
    onWordItemClicked: () -> Unit = {},
    onPracticeItemClicked: () -> Unit = {},
    onCategoryClicked: () -> Unit = {},
    onCategoryCardClicked: (categoryName: String) -> Unit,

    onPracticeBtnClicked: () -> Unit,
) {
    val categoryViewModel: CategoryViewModel = hiltViewModel()
    CategoryContent(
        onWordItemClicked = onWordItemClicked,
        onPracticeItemClicked = onPracticeItemClicked,
        onCategoryItemClicked = onCategoryClicked,
        onCategoryCardClicked = onCategoryCardClicked,
        wordViewModel = wordViewModel,
        onSwipeDismiss = {
            categoryViewModel.deleteCategory(it)
        },
        onPracticeBtnClicked = onPracticeBtnClicked
    )
}