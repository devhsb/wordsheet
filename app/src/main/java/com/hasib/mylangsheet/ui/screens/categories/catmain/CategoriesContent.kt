package com.hasib.mylangsheet.ui.screens.categories.catmain

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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

                }
            )
        }

    ) {

        Box(
            modifier = Modifier.padding(it)
        ) {

        }
    }
}



























