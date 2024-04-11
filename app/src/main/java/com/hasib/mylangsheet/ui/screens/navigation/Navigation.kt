package com.hasib.mylangsheet.ui.screens.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hasib.mylangsheet.ui.screens.practice.practicemain.PracticeScreen
import com.hasib.mylangsheet.ui.screens.words.wordmain.WordViewModel
import com.hasib.mylangsheet.ui.screens.words.wordmain.WordsScreen
import com.hasib.mylangsheet.util.Constants.PRACTICE_SCREEN
import com.hasib.mylangsheet.util.Constants.WORDS_SCREEN

@Composable
fun Navigation(
    navController: NavHostController,
    wordViewModel: WordViewModel
) {
    NavHost(
        navController = navController,
        startDestination = WORDS_SCREEN,
    ) {

        composable(route = WORDS_SCREEN) {

            WordsScreen(
                wordViewModel = wordViewModel,
                onWordItemClicked = {
                    wordViewModel.topbarDropDownState.value = false
                    navController.popBackStack(
                        route = WORDS_SCREEN, inclusive = false
                    )
                },
                onPracticeItemClicked = {
                    wordViewModel.topbarDropDownState.value = false
                    navController.navigate(PRACTICE_SCREEN)
                }
            )

        }

        composable(route = PRACTICE_SCREEN) {
            PracticeScreen(
                wordViewModel = wordViewModel,
                onWordItemClicked = {
                    wordViewModel.topbarDropDownState.value = false
                    navController.popBackStack(
                        route = WORDS_SCREEN, inclusive = false
                    )
                },
                onPracticeItemClicked = {
                    wordViewModel.topbarDropDownState.value = false
                    navController.navigate(PRACTICE_SCREEN)
                }
            )
        }
    }
}