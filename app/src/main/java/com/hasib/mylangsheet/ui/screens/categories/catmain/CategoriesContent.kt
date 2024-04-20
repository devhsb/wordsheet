package com.hasib.mylangsheet.ui.screens.categories.catmain

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hasib.mylangsheet.ui.screens.categories.catdialog.CatDialog
import com.hasib.mylangsheet.ui.screens.categories.catdialog.CatDialogViewModel
import com.hasib.mylangsheet.ui.screens.words.wordmain.Action
import com.hasib.mylangsheet.ui.screens.words.wordmain.WordViewModel
import com.hasib.mylangsheet.ui.shared_components.BottomBar
import com.hasib.mylangsheet.ui.shared_components.CenterAlignedTopAppbar

@Composable
fun CategoryContent(
    wordViewModel: WordViewModel,
    onWordItemClicked: () -> Unit = {},
    onPracticeItemClicked: () -> Unit = {},
    onCategoryItemClicked: () -> Unit = {},
) {
    val catDialogViewModel = wordViewModel.catDialogViewModel
    val catDialogUiState by catDialogViewModel.catDialogUiState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppbar(
                title = "Categories",
                onWordItemClicked = onWordItemClicked,
                onPracticeItemClicked = onPracticeItemClicked,
                onCategoryItemClicked = onCategoryItemClicked,
                wordViewModel = wordViewModel
            )
        },

        bottomBar = {
            BottomBar(
                addBtnText = "CREATE",

                onAddButtonClicked = {
                    catDialogViewModel.updateCatDialogState(isCatDialogOpen = true)
                }
            )
        }

    ) {

        Box(
            modifier = Modifier.padding(it)
        ) {
            if(catDialogUiState.isCatDialogOpen) {
                CatDialog(wordViewModel = wordViewModel)
            }
        }
    }
}



























