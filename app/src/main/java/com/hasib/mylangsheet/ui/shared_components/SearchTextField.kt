package com.hasib.mylangsheet.ui.shared_components

import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusRestorer
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import com.hasib.mylangsheet.ui.screens.words.wordmain.WordViewModel

//@Preview(showSystemUi = true)
//@Composable
//fun SearchTextFieldPreview() {
//    SearchTextField(viewModel())
//}

@OptIn(ExperimentalComposeUiApi::class)
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    wordViewModel: WordViewModel
) {
    val wordUiState by wordViewModel.wordUiState.collectAsState()
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        modifier = modifier
    ) {

        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }

        BackHandler(onBack = {
            wordViewModel.resetWordState()
            wordViewModel.getAllWords()
        })
        TextField(
            modifier = Modifier.fillMaxWidth()
                .focusRequester(focusRequester),
            singleLine = true,
            textStyle = TextStyle(fontSize = MaterialTheme.typography.titleMedium.fontSize),
            value = wordUiState.searchQuery,
            onValueChange = {
                wordViewModel.updateWordState(searchQuery = it)
                wordViewModel.searchDatabase(it)
            },
            placeholder = { Text("Search word", color = MaterialTheme.colorScheme.onSecondaryContainer) },
            colors = TextFieldDefaults.colors(
                unfocusedPlaceholderColor = Color.Gray
            ),

            leadingIcon = {
                IconButton(onClick = {
                    wordViewModel.resetWordState()
                    wordViewModel.getAllWords()
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            },

            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),

            keyboardActions = KeyboardActions {
                keyboardController?.hide()
            }
        )
    }

}

