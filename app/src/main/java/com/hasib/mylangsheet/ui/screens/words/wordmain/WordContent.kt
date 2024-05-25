package com.hasib.mylangsheet.ui.screens.words.wordmain

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hasib.mylangsheet.data.room.entites.word.Word
import com.hasib.mylangsheet.ui.screens.words.actions.DbAction
import com.hasib.mylangsheet.ui.screens.words.dialog.DialogViewModel
import com.hasib.mylangsheet.ui.shared_components.BottomBar
import com.hasib.mylangsheet.ui.shared_components.CenterAlignedTopAppbar
import com.hasib.mylangsheet.ui.screens.words.dialog.NewWordDialog
import com.hasib.mylangsheet.ui.screens.words.dialog.SimpleDialog
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun WordScreenContent(
    wordViewModel: WordViewModel,
    onWordItemClicked: () -> Unit,
    onPracticeItemClicked: () -> Unit,
    onCategoryItemClicked: () -> Unit,
    wordList: List<Word>,
    appBarTitle: String,

    onPracticeBtnClicked: () -> Unit,
) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        wordViewModel.textToSpeach(context, "")
    }

    WordScreenBody(
        modifier = Modifier.padding(10.dp),
        wordViewModel = wordViewModel,
        onWordItemClicked = onWordItemClicked,
        onPracticeItemClicked = onPracticeItemClicked,
        onCategoryItemClicked = onCategoryItemClicked,
        wordList = wordList,
        appBarTitle = appBarTitle,
        onPracticeBtnClicked = onPracticeBtnClicked
    )
}

@RequiresApi(Build.VERSION_CODES.N)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
private fun WordScreenBody(
    modifier: Modifier = Modifier,
    wordViewModel: WordViewModel,
    onWordItemClicked: () -> Unit,
    onPracticeItemClicked: () -> Unit,
    onCategoryItemClicked: () -> Unit,
    wordList: List<Word>,
    appBarTitle: String,
    onPracticeBtnClicked: () -> Unit,
) {

    val dialogViewModel = wordViewModel.dialogViewModel
    val dialogUiState by dialogViewModel.dialogUiState.collectAsState()

    var action by wordViewModel.dbAction

    Scaffold(
        topBar = {
            CenterAlignedTopAppbar(
                title = appBarTitle,
                wordViewModel = wordViewModel,
                onWordItemClicked = onWordItemClicked,
                onPracticeItemClicked = onPracticeItemClicked,
                onCategoryItemClicked = onCategoryItemClicked
            )
        },
        bottomBar = {
            BottomBar(
                btnText = "Add",
                onAddButtonClicked = {
                    action = DbAction.INSERT
                    dialogViewModel.isDialogOpen()
                },
                onPracticeBtnClicked = onPracticeBtnClicked
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
                        action = DbAction.DELETE
                        dialogViewModel.isSimpleDialogOpen()
                        wordViewModel.handleDatabase()
                    },

                    onEditPressed = {
                        action = DbAction.UPDATE
                        dialogViewModel.updateDialogState(
                            isSimpleDialogOpen = false,
                            isDialogOpen = true
                        )
                    },
                    wordMeaning = dialogUiState.selectedWord.wordMeaning
                )
            }
            WordList(
                wordList = wordList,
                dialogViewModel = dialogViewModel,
                wordViewModel = wordViewModel
            )
        }
    }
}


@Composable
private fun WordList(
    modifier: Modifier = Modifier,
    wordList: List<Word>,
    dialogViewModel: DialogViewModel,
    wordViewModel: WordViewModel,
) {
    val context = LocalContext.current

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 7.dp,
        horizontalArrangement = Arrangement.spacedBy(7.dp),
        modifier = modifier
            .fillMaxWidth()
    ) {
        items(
            items = wordList,
            key = { it.word }
        ) { word ->

            WordCard(
                word = word,
                openSimpleDialog = {
                    dialogViewModel.updateDialogState(
                        selectedWord = word,
                        oldWord = word.word,
                        isSimpleDialogOpen = true
                    )
                },

                onTextToSpeechBtnClicked = {
                    wordViewModel.textToSpeach(
                        context = context,
                        text = word.word
                    )
                }
            )
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WordCard(
    modifier: Modifier = Modifier,
    word: Word,
    openSimpleDialog: () -> Unit = {},
    onLongPress: () -> Unit = {},
    onTextToSpeechBtnClicked: () -> Unit = {}
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    Card(
        modifier = modifier
            .width(
                (screenWidth / 2).dp
            )
            .clickable(onClick = openSimpleDialog)
            .combinedClickable(
                onClick = openSimpleDialog,
                onLongClick = onLongPress,
                onLongClickLabel = null
            )
            .border(
                1.dp,
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.outline.copy(alpha = .2f)
            ),

        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onBackground.copy(alpha = .1f),
        )
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .offset(y = 5.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = word.word.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
                style = MaterialTheme.typography.titleSmall,
                letterSpacing = 3.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                textAlign = TextAlign.Center,
            )
        }

        IconButton(onClick = onTextToSpeechBtnClicked) {
            Icon(
                imageVector = Icons.Filled.PlayArrow,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier
                    .alpha(.7f)
                    .align(Alignment.Start)
            )
        }

    }
}

@Preview
@Composable
fun WordCardPreview() {
    WordCard(word = Word("Test", "Test"))
}


















