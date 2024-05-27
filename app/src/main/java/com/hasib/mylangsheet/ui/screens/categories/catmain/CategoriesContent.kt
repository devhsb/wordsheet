package com.hasib.mylangsheet.ui.screens.categories.catmain

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hasib.mylangsheet.ui.screens.categories.catdialog.CatDialog
import com.hasib.mylangsheet.ui.screens.words.wordmain.WordViewModel
import com.hasib.mylangsheet.ui.shared_components.BottomBar
import com.hasib.mylangsheet.ui.shared_components.CenterAlignedTopAppbar
import com.hasib.mylangsheet.data.room.entites.category.Category
import com.hasib.mylangsheet.ui.shared_components.RedBackground
import com.hasib.mylangsheet.ui.theme.MyLangsheetTheme
import kotlinx.coroutines.delay

@RequiresApi(Build.VERSION_CODES.N)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CategoryContent(
    wordViewModel: WordViewModel,
    onWordItemClicked: () -> Unit = {},
    onPracticeItemClicked: () -> Unit = {},
    onCategoryItemClicked: () -> Unit = {},
    onSwipeDismiss: (Category) -> Unit,
    onCategoryCardClicked: (categoryName: String) -> Unit,

    onPracticeBtnClicked: () -> Unit,

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
                btnText = "CREATE",

                onAddButtonClicked = {
                    catDialogViewModel.updateCatDialogState(isCatDialogOpen = true)
                },
                onPracticeBtnClicked = onPracticeBtnClicked
            )
        }

    ) {

        Box(modifier = Modifier.padding(it)) {

            if (catDialogUiState.isCatDialogOpen) {
                CatDialog(wordViewModel = wordViewModel)
            }

            CategoryList(
                categoryList = categories,
                onSwipeDismiss = onSwipeDismiss,
                onCategoryCardClicked = onCategoryCardClicked
            )
        }

    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CategoryList(
    categoryList: List<Category>,
    onSwipeDismiss: (Category) -> Unit,
    onCategoryCardClicked: (categoryName: String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(
            items = categoryList,
            key = { it.categoryName }
        ) { category ->

            val dismissState = rememberDismissState()
            val isDismissed = dismissState.isDismissed(DismissDirection.EndToStart)

            if (isDismissed) {
                LaunchedEffect(key1 = true) {
                    delay(300)
                    onSwipeDismiss(category)
                }
            }

            val degree by animateFloatAsState(
                targetValue = if (dismissState.targetValue == DismissValue.Default) 0f else -45f,
                label = "delete button degree"
            )

            var itemAppeared by remember { mutableStateOf(false) }
            LaunchedEffect(key1 = true) {
                itemAppeared = true
            }

            AnimatedVisibility(
                visible = itemAppeared && !isDismissed,
                enter = expandVertically(animationSpec = tween(durationMillis = 300)),
                exit = shrinkVertically(animationSpec = tween(durationMillis = 300)),
            ) {
                SwipeToDismiss(
                    state = dismissState,
                    directions = setOf(DismissDirection.EndToStart),
                    background = { RedBackground(degree) },
                    dismissContent = {
                        CategoryCard(
                            category = category,
                            onCategoryCardClicked = onCategoryCardClicked
                        )
                    }
                )
            }

        }
    }
}


@Composable
private fun CategoryCard(
    category: Category,
    onCategoryCardClicked: (categoryName: String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(5.dp)
            .clickable {onCategoryCardClicked(category.categoryName)},
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
        CategoryCard(Category("jam")) {}
    }
}






















