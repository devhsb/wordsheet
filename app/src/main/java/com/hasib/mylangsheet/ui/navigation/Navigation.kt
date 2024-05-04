package com.hasib.mylangsheet.ui.navigation

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hasib.mylangsheet.ui.screens.categories.catmain.CategoryScreen
import com.hasib.mylangsheet.ui.screens.practice.practicemain.PracticeScreen
import com.hasib.mylangsheet.ui.screens.words.wordmain.WordViewModel
import com.hasib.mylangsheet.ui.screens.words.wordmain.WordsScreen
import com.hasib.mylangsheet.util.Constants.CATEGORY_SCREEN
import com.hasib.mylangsheet.util.Constants.PRACTICE_SCREEN
import com.hasib.mylangsheet.util.Constants.WORDS_SCREEN

enum class NavigationType {
    WORD_SCREEN,
    PRACTICE_SCREEN,
    CATEGORY_SCREEN
}

@Composable
fun Navigation(
    navController: NavHostController,
    wordViewModel: WordViewModel
) {
    val context = LocalContext.current
    val categoryWords by wordViewModel.categoryWords.collectAsState()

    val allWords = wordViewModel.allWords.collectAsState().value

    val dialogViewModel = wordViewModel.dialogViewModel



    NavHost(
        navController = navController,
        startDestination = WORDS_SCREEN,
    ) {

        composable(
            route = WORDS_SCREEN,
            arguments = listOf(navArgument("category") { type = NavType.StringType })
        ) { backStackEntry ->

            Log.d("TAG", "Navigation: ${backStackEntry.arguments?.getString("category")}")

            var categoryName by remember {
                mutableStateOf(
                    backStackEntry.arguments?.getString("category")
                )
            }

            val dialogCategoryTitle = if (categoryName == "All") "general" else categoryName ?: "general"
            dialogViewModel.updateDialogState(
                dialogTitle = "Add Into $dialogCategoryTitle",
                categoryName = dialogCategoryTitle
            )

            WordsScreen(
                wordViewModel = wordViewModel,
                appBarTitle = categoryName ?: "All",
                wordList = if (categoryName == "All" || categoryName == null) allWords else categoryWords,


                onWordItemClicked = {
                    categoryName = "All"
                    navigate(
                        navigationType = NavigationType.WORD_SCREEN,
                        wordViewModel = wordViewModel,
                        navController = navController
                    )
                },

                onPracticeItemClicked = {
                    navigate(
                        navigationType = NavigationType.PRACTICE_SCREEN,
                        wordViewModel = wordViewModel,
                        navController = navController,
                        context = context
                    )
                },

                onCategoryItemClicked = {
                    navigate(
                        navigationType = NavigationType.CATEGORY_SCREEN,
                        navController = navController,
                        wordViewModel = wordViewModel
                    )
                }
            )

        }

        composable(route = PRACTICE_SCREEN) {


            PracticeScreen(
                wordViewModel = wordViewModel,

                onWordItemClicked = {
                    navigate(
                        navigationType = NavigationType.WORD_SCREEN,
                        wordViewModel = wordViewModel,
                        navController = navController
                    )
                },

                onPracticeItemClicked = {
                    navigate(
                        navigationType = NavigationType.PRACTICE_SCREEN,
                        wordViewModel = wordViewModel,
                        navController = navController,
                        context = context
                    )
                },

                onCategoryItemClicked = {
                    navigate(
                        navigationType = NavigationType.CATEGORY_SCREEN,
                        navController = navController,
                        wordViewModel = wordViewModel
                    )
                }


            )
        }

        composable(route = CATEGORY_SCREEN) {
            CategoryScreen(
                wordViewModel = wordViewModel,

                onWordItemClicked = {
                    navigate(
                        navigationType = NavigationType.WORD_SCREEN,
                        wordViewModel = wordViewModel,
                        navController = navController
                    )
                },

                onPracticeItemClicked = {
                    navigate(
                        navigationType = NavigationType.PRACTICE_SCREEN,
                        wordViewModel = wordViewModel,
                        navController = navController,
                        context = context
                    )
                },

                onCategoryClicked = {
                    navigate(
                        navigationType = NavigationType.CATEGORY_SCREEN,
                        navController = navController,
                        wordViewModel = wordViewModel
                    )
                },

                onCategoryCardClicked = { category ->
                    wordViewModel.getCategoryWords(category)
                    navController.navigate(route = "words/$category")
                }
            )
        }
    }
}


private fun navigate(
    navigationType: NavigationType,
    wordViewModel: WordViewModel,
    navController: NavHostController,
    context: Context? = null
) {
    wordViewModel.topbarDropDownState.value = false

    when (navigationType) {

        NavigationType.WORD_SCREEN -> {
            navController.popBackStack(route = WORDS_SCREEN, inclusive = false)
        }

        NavigationType.PRACTICE_SCREEN -> {
            if (wordViewModel.categoryWords.value.isEmpty()) {
                Toast.makeText(context, "Please add some words first", Toast.LENGTH_SHORT)
                    .show()
            } else {
                navController.navigate(PRACTICE_SCREEN)
            }
        }

        NavigationType.CATEGORY_SCREEN -> {
            navController.navigate(CATEGORY_SCREEN)
        }
    }
}

















