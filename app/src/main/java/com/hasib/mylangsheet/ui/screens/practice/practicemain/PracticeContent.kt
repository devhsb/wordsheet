package com.hasib.mylangsheet.ui.screens.practice.practicemain

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hasib.mylangsheet.ui.screens.words.wordmain.WordViewModel
import com.hasib.mylangsheet.ui.shared_components.CenterAlignedTopAppbar

@Composable
fun PracticeContent(
    wordViewModel: WordViewModel,
    onWordItemClicked: () -> Unit,
    onPracticeItemClicked: () -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppbar(
                title = "Practice",
                onWordItemClicked = onWordItemClicked,
                onPracticeItemClicked = onPracticeItemClicked,
                wordViewModel = wordViewModel
            )
        }

    ) {
        Box(modifier = Modifier.padding(it)){}
    }
}