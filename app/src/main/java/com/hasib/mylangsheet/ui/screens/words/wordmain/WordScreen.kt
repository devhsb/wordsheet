package com.hasib.mylangsheet.ui.screens.words.wordmain

import androidx.compose.runtime.Composable
import com.hasib.mylangsheet.data.room.entites.word.Word

@Composable
fun WordsScreen(
    wordViewModel: WordViewModel,
    onWordItemClicked: () -> Unit,
    onPracticeItemClicked: () -> Unit,
    onCategoryItemClicked: () -> Unit,
    wordList: List<Word>
) {
    WordScreenContent(
        wordViewModel = wordViewModel,
        onWordItemClicked = onWordItemClicked,
        onPracticeItemClicked = onPracticeItemClicked,
        onCategoryItemClicked = onCategoryItemClicked,
        wordList = wordList
    )
}