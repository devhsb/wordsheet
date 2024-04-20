package com.hasib.mylangsheet.ui.navigation

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
    NavHost(
        navController = navController,
        startDestination = WORDS_SCREEN,
    ) {

        composable(route = WORDS_SCREEN) {

            WordsScreen(
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
            if (wordViewModel.wordList.value.isEmpty()) {
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

















