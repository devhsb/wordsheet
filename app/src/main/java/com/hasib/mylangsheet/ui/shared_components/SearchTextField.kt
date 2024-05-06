package com.hasib.mylangsheet.ui.shared_components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.hasib.mylangsheet.ui.screens.words.wordmain.WordViewModel

//@Preview(showSystemUi = true)
//@Composable
//fun SearchTextFieldPreview() {
//    SearchTextField(viewModel())
//}

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    wordViewModel: WordViewModel
) {
    val wordUiState by wordViewModel.wordUiState.collectAsState()

    Row(
        modifier = modifier
    ) {

        BackHandler(onBack = { wordViewModel.updateWordState(isSearchActive = false) })

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = wordUiState.searchQuery,
            onValueChange = {
                wordViewModel.updateWordState(searchQuery = it)
                wordViewModel.searchDatabase(it)
            },
            placeholder = { Text("Search word") },
            colors = TextFieldDefaults.colors(
                unfocusedPlaceholderColor = Color.Gray
            ),

            leadingIcon = {
                IconButton(onClick = { wordViewModel.updateWordState(isSearchActive = false) }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
        )
    }

}

