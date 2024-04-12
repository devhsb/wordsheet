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

                openSimpleDialog = {

                    if(passedWord.isNotEmpty()) {
                        randomWord = passedWord.random()
                        passedWord.remove(randomWord)
                    }

                    Log.d("TAG", "PracticeContent: $passedWord")

                }
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
            onClick = openSimpleDialog,
        ) {
            Text(text = "Next")
        }
    }
}


fun RandomWord(
    wordList: List<Word>
): Int {
    return Random.nextInt(0, wordList.size - 1)
}






















