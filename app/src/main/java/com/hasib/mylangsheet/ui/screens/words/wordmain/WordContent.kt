package com.hasib.mylangsheet.ui.screens.words.wordmain

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hasib.mylangsheet.data.room.entites.Word
import com.hasib.mylangsheet.ui.screens.words.dialog.DialogViewModel
import com.hasib.mylangsheet.ui.shared_components.BottomBar
import com.hasib.mylangsheet.ui.shared_components.CenterAlignedTopAppbar
import com.hasib.mylangsheet.ui.screens.words.dialog.NewWordDialog
import com.hasib.mylangsheet.ui.screens.words.dialog.SimpleDialog

@Composable
fun WordScreenContent(
    modifier: Modifier = Modifier,
    wordViewModel: WordViewModel,
    onWordItemClicked: () -> Unit,
    onPracticeItemClicked: () -> Unit,
    onCategoryItemClicked: () -> Unit
) {

    WordScreenBody(
        modifier = Modifier.padding(10.dp),
        wordViewModel = wordViewModel,
        onWordItemClicked = onWordItemClicked,
        onPracticeItemClicked = onPracticeItemClicked,
        onCategoryItemClicked = onCategoryItemClicked

    )
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
private fun WordScreenBody(
    modifier: Modifier = Modifier,
    wordViewModel: WordViewModel,
    onWordItemClicked: () -> Unit,
    onPracticeItemClicked: () -> Unit,
    onCategoryItemClicked: () -> Unit
) {

    val dialogViewModel = wordViewModel.dialogViewModel
    val dialogUiState by dialogViewModel.dialogUiState.collectAsState()

    var action by wordViewModel.action

    val wordList by wordViewModel.wordList.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppbar(
                title = "Words",
                wordViewModel = wordViewModel,
                onWordItemClicked = onWordItemClicked,
                onPracticeItemClicked = onPracticeItemClicked,
                onCategoryItemClicked = onCategoryItemClicked,

            )
        },
        bottomBar = {
            BottomBar(
                addBtnText = "ADD",
                onAddButtonClicked = {
                    action = Action.INSERT
                    dialogViewModel.isDialogOpen()
                }
            )
        }
    ) {
        Box(
            modifier = modifier
                .padding(it)
                .fillMaxSize()
        ) {

            if (dialogUiState.isDialogOpen) {
                NewWordDialog(
                    modifier = Modifier
                        .padding(top = 0.dp),
                    wordViewModel = wordViewModel,
                )
            }

            if (dialogUiState.isSimpleDialogOpen) {
                SimpleDialog(
                    isOpened = {
                        dialogViewModel.isSimpleDialogOpen()
                        dialogViewModel.resetDialogState()
                    },

                    onDeletePressed = {
                        action = Action.DELETE
                        dialogViewModel.isSimpleDialogOpen()
                        wordViewModel.handleDatabase()
                    },

                    onEditPressed = {
                        action = Action.UPDATE
                        dialogViewModel.updateDialogState(
                            isSimpleDialogOpen = false,
                            isDialogOpen = true
                        )
                    },
                    wordMeaning = dialogUiState.meaningTextFieldValue
                )
            }
            WordList(
                wordList = wordList,
                dialogViewModel = dialogViewModel
            )
        }
    }
}

@Composable
private fun WordList(
    modifier: Modifier = Modifier,
    wordList: List<Word>,
    dialogViewModel: DialogViewModel
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
    ) {
        items(
            items = wordList,
            key = { it.id }
        ) { word ->

            val cardOffset = if (word.id % 2 == 0) {
                DpOffset(x = (screenWidth / 2.3).dp, y = 0.dp)
            } else {
                DpOffset(x = 0.dp, y = 0.dp)
            }

            WordCard(
                word = word,
                offset = cardOffset,
                openSimpleDialog = {
                    dialogViewModel.updateDialogState(
                        wordTextFieldValue = word.word,
                        meaningTextFieldValue = word.wordMeaning,
                        currentWordId = word.id,
                        isSimpleDialogOpen = true
                    )
                }
            )
        }
    }
}


@Composable
fun WordCard(
    modifier: Modifier = Modifier,
    offset: DpOffset = DpOffset(0.dp,0.dp),
    word: Word,
    openSimpleDialog: () -> Unit
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    Card(
        modifier = modifier
            .width(
                (screenWidth / 2).dp
            )
            .height(60.dp)
            .padding(vertical = 5.dp)
            .offset(x = offset.x)
            .clickable(onClick = openSimpleDialog),
        shape = MaterialTheme.shapes.extraLarge,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    ) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = word.word,
                style = MaterialTheme.typography.titleMedium,
                letterSpacing = 3.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }

    }
}


















