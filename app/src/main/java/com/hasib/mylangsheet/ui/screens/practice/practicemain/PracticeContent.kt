package com.hasib.mylangsheet.ui.screens.practice.practicemain

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hasib.mylangsheet.data.model.Word
import com.hasib.mylangsheet.ui.screens.words.wordmain.WordCard
import com.hasib.mylangsheet.ui.screens.words.wordmain.WordViewModel
import com.hasib.mylangsheet.ui.shared_components.CenterAlignedTopAppbar

@Composable
fun PracticeContent(
    wordViewModel: WordViewModel,
    onWordItemClicked: () -> Unit = {},
    onPracticeItemClicked: () -> Unit = {},
) {

    val words by wordViewModel.wordList.collectAsState()

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
        Box(modifier = Modifier.padding(it)) {
            PracticeBody(
                word = words[1],
                openSimpleDialog = {}
            )
        }
    }
}

@Composable
private fun PracticeBody(
    word: Word,
    openSimpleDialog: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        WordCard(
            word = word,
            openSimpleDialog = openSimpleDialog
        )

        Spacer(modifier = Modifier.height(70.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            onClick = { /*TODO*/ },
        ) {
            Text(text = "Next")
        }
    }
}

