package com.hasib.mylangsheet.ui.screens.practice.practicemain

import android.util.Log
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hasib.mylangsheet.data.model.Word
import com.hasib.mylangsheet.ui.screens.words.wordmain.WordCard
import com.hasib.mylangsheet.ui.screens.words.wordmain.WordViewModel
import com.hasib.mylangsheet.ui.shared_components.CenterAlignedTopAppbar
import kotlin.random.Random

@Composable
fun PracticeContent(
    wordViewModel: WordViewModel,
    onWordItemClicked: () -> Unit = {},
    onPracticeItemClicked: () -> Unit = {},
) {

    val words by wordViewModel.wordList.collectAsState()

    val passedWord: MutableList<Word> = words.toMutableList()

    var randomWord by remember {
        mutableStateOf(passedWord.random())
    }

    var wordPosition by remember {
        mutableIntStateOf(1)
    }


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
                word = randomWord,
                wordCount = "$wordPosition / ${words.size}",

                openSimpleDialog = {
                    if(passedWord.isNotEmpty()) {

                        passedWord.remove(randomWord)

                        if(passedWord.isNotEmpty()) {
                            wordPosition++
                            randomWord = passedWord.random()
                        }
                    }

                }
            )

        }
    }
}

@Preview
@Composable
fun PracticeBodyPreview() {
    PracticeBody(word = Word(0, "Test", "test")) {

    }
}
@Composable
private fun PracticeBody(
    word: Word,
    wordCount: String = "0/0",
    openSimpleDialog: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
    ) {

        Text(
            text = wordCount,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            style = MaterialTheme.typography.titleSmall
        )

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WordCard(
                word = word,
                openSimpleDialog = {}
            )

            Spacer(modifier = Modifier.height(70.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .height(50.dp),
                onClick = openSimpleDialog,
            ) {
                Text(text = "Next")
            }
        }
    }
}


fun RandomWord(
    wordList: List<Word>
): Int {
    return Random.nextInt(0, wordList.size - 1)
}
 



















