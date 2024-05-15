package com.hasib.mylangsheet.ui.screens.practice.practicemain

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.hasib.mylangsheet.data.room.entites.word.Word
import com.hasib.mylangsheet.ui.screens.words.dialog.DialogViewModel
import com.hasib.mylangsheet.ui.screens.words.wordmain.WordCard
import com.hasib.mylangsheet.ui.screens.words.wordmain.WordViewModel
import com.hasib.mylangsheet.ui.shared_components.CenterAlignedTopAppbar
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun PracticeContent(
    words: List<Word>,
    wordViewModel: WordViewModel,
    onWordItemClicked: () -> Unit = {},
    onPracticeItemClicked: () -> Unit = {},
    onCategoryClicked: () -> Unit = {},
) {

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
                onCategoryItemClicked = onCategoryClicked,
                wordViewModel = wordViewModel
            )
        }

    ) {
        Box(modifier = Modifier.padding(it)) {

            PracticeBody(
                word = randomWord,
                wordCount = "$wordPosition / ${words.size}",

                openSimpleDialog = {
                    if (passedWord.isNotEmpty()) {
                        passedWord.remove(randomWord)
                        if (passedWord.isNotEmpty()) {
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
    PracticeBody(word = Word("Test", "test")) {

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PracticeBody(
    word: Word,
    wordCount: String = "0/0",
    openSimpleDialog: () -> Unit
) {
    val haptics = LocalHapticFeedback.current
    val dialogViewModel = DialogViewModel()
    val context = LocalContext.current

    var isDialogOpened by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
    ) {

        if (isDialogOpened) {
            MeaningDialog(
                onDismiss = { isDialogOpened = false },
                word = word
            )

            LaunchedEffect(Unit) {
                delay(1000)
                isDialogOpened = false
            }
        }

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
                modifier = Modifier
                    .height(70.dp),
                word = word,
                onLongPress = {
                    haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                    isDialogOpened = true
                }
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

@Composable
private fun MeaningDialog(
    onDismiss: () -> Unit = {},
    word: Word
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .padding(10.dp)
                .background(
                    MaterialTheme.colorScheme.surfaceVariant,
                    shape = MaterialTheme.shapes.large
                )
                .padding(10.dp)
        ) {

            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = word.wordMeaning,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
}

@Preview
@Composable
private fun MeaningDialogPreview() {
    MeaningDialog(word = Word(word = "123", wordMeaning = "meaningmeaningmeaningmeaningmeaning"))
}








