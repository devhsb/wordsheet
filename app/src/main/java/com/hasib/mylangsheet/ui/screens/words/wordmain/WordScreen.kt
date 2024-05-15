package com.hasib.mylangsheet.ui.screens.words.wordmain

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import com.hasib.mylangsheet.data.room.entites.word.Word

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun WordsScreen(
    wordViewModel: WordViewModel,
    onWordItemClicked: () -> Unit,
    onPracticeItemClicked: () -> Unit,
    onCategoryItemClicked: () -> Unit,
    wordList: List<Word>,
    appBarTitle: String,

    onPracticeBtnClicked: () -> Unit,
) {
    WordScreenContent(
        wordViewModel = wordViewModel,
        onWordItemClicked = onWordItemClicked,
        onPracticeItemClicked = onPracticeItemClicked,
        onCategoryItemClicked = onCategoryItemClicked,
        wordList = wordList,
        appBarTitle = appBarTitle,
        onPracticeBtnClicked = onPracticeBtnClicked
    )
}