package com.hasib.mylangsheet.ui.screens.practice.practicemain

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import com.hasib.mylangsheet.data.room.entites.word.Word
import com.hasib.mylangsheet.ui.screens.words.wordmain.WordViewModel

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun PracticeScreen(
    words: List<Word>,
    wordViewModel: WordViewModel,
    onWordItemClicked: () -> Unit,
    onPracticeItemClicked: () -> Unit,
    onCategoryItemClicked: () -> Unit
) {
    PracticeContent(
        words = words,
        wordViewModel = wordViewModel,
        onWordItemClicked = onWordItemClicked,
        onPracticeItemClicked = onPracticeItemClicked,
        onCategoryClicked = onCategoryItemClicked
    )
}