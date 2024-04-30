package com.hasib.mylangsheet.ui.screens.categories.catmain

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hasib.mylangsheet.ui.screens.categories.catdialog.CatDialog
import com.hasib.mylangsheet.ui.screens.categories.catdialog.CatDialogViewModel
import com.hasib.mylangsheet.ui.screens.words.wordmain.Action
import com.hasib.mylangsheet.ui.screens.words.wordmain.WordViewModel
import com.hasib.mylangsheet.ui.shared_components.BottomBar
import com.hasib.mylangsheet.ui.shared_components.CenterAlignedTopAppbar
import com.hasib.mylangsheet.data.room.entites.Category
import com.hasib.mylangsheet.ui.theme.MyLangsheetTheme

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CategoryContent(
    wordViewModel: WordViewModel,
    onWordItemClicked: () -> Unit = {},
    onPracticeItemClicked: () -> Unit = {},
    onCategoryItemClicked: () -> Unit = {},
) {
    val catDialogViewModel = wordViewModel.catDialogViewModel
    val catDialogUiState by catDialogViewModel.catDialogUiState.collectAsState()

    val categoryViewModel: CategoryViewModel = hiltViewModel()
    val categories by categoryViewModel.categoryList.collectAsState()

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

        Box(modifier = Modifier.padding(it)) {

            if (catDialogUiState.isCatDialogOpen) {
                CatDialog(wordViewModel = wordViewModel)
            }

            CategoryList(categoryList = categories)
        }

    }
}

@Composable
private fun CategoryList(
    categoryList: List<Category>
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(
            items = categoryList,
            key = { it.categoryId }
        ) { category ->
            CategoryCard(category = category)
        }
    }
}


@Composable
private fun CategoryCard(
    category: Category
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(5.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center

        ) {
            Text(
                text = category.categoryName,
                style = MaterialTheme.typography.titleMedium,
                letterSpacing = 3.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}


@Preview
@Composable
private fun CategoryCardPrevie() {
    MyLangsheetTheme(useDarkTheme = true) {
        CategoryCard(Category(0, "janm"))
    }
}






















