package com.hasib.mylangsheet.ui.screens.words.wordmain

import android.annotation.SuppressLint
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hasib.mylangsheet.data.room.entites.word.Word
import com.hasib.mylangsheet.ui.screens.words.actions.DbAction
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
    onCategoryItemClicked: () -> Unit,
    wordList: List<Word>,
    appBarTitle: String
) {

    WordScreenBody(
        modifier = Modifier.padding(10.dp),
        wordViewModel = wordViewModel,
        onWordItemClicked = onWordItemClicked,
        onPracticeItemClicked = onPracticeItemClicked,
        onCategoryItemClicked = onCategoryItemClicked,
        wordList = wordList,
        appBarTitle = appBarTitle

    )
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
private fun WordScreenBody(
    modifier: Modifier = Modifier,
    wordViewModel: WordViewModel,
    onWordItemClicked: () -> Unit,
    onPracticeItemClicked: () -> Unit,
    onCategoryItemClicked: () -> Unit,
    wordList: List<Word>,
    appBarTitle: String
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
                onCategoryItemClicked = onCategoryItemClicked,

            )
        },
        bottomBar = {
            BottomBar(
                addBtnText = "ADD",
                onAddButtonClicked = {
                    action = DbAction.INSERT
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
                        action = DbAction.DELETE
                        dialogViewModel.isSimpleDialogOpen()
                        wordViewModel.handleDatabase()
                    },

                    onEditPressed = {
                        action = DbAction.MANUAL_UPDATE
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
    word: Word,
    openSimpleDialog: () -> Unit
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    Card(
        modifier = modifier
            .width(
                (screenWidth / 2).dp
            )
            .clickable(onClick = openSimpleDialog),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    ) {

        Box(
            modifier = Modifier.fillMaxSize().padding(15.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = word.word,
                style = MaterialTheme.typography.titleSmall,
                letterSpacing = 3.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }

    }
}


















