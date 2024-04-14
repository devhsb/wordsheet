package com.hasib.mylangsheet.ui.navigation


import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
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
    val context = LocalContext.current
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
                    if(wordViewModel.wordList.value.isEmpty()){
                        Toast.makeText(context, "Please add some words first", Toast.LENGTH_SHORT).show()
                    }else {
                        navController.navigate(PRACTICE_SCREEN)
                    }
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