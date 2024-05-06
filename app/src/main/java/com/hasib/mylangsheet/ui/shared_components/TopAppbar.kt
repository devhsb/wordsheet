package com.hasib.mylangsheet.ui.shared_components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hasib.mylangsheet.ui.screens.words.wordmain.WordViewModel
import com.hasib.mylangsheet.ui.theme.MyLangsheetTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterAlignedTopAppbar(
    title: String,
    onWordItemClicked: () -> Unit,
    onPracticeItemClicked: () -> Unit,
    onCategoryItemClicked: () -> Unit,
    wordViewModel: WordViewModel
) {
    val wordUiState by wordViewModel.wordUiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        var expanded by wordViewModel.topbarDropDownState

        CenterAlignedTopAppBar(title = {
            if(wordUiState.isSearchActive) {
                SearchTextField(
                    wordViewModel = wordViewModel
                )
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .clickable { expanded = !expanded },
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowDown,
                            contentDescription = null
                        )
                    }

                    IconButton(
                        onClick = { wordViewModel.updateWordState(isSearchActive = true) }) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = null,
                        )
                    }
                }
            }
        })


        DropdownMenu(
            modifier = Modifier
                .fillMaxWidth(),
            expanded = expanded,
            onDismissRequest = { expanded = !expanded }) {

            DropdownMenuItem(
                text = "Words",
                onItemClicked = onWordItemClicked
            )
            DropdownMenuItem(
                text = "Practice",
                onItemClicked = onPracticeItemClicked
            )
            DropdownMenuItem(
                text = "Categories",
                onItemClicked = onCategoryItemClicked
            )
        }

    }
}

@Composable
fun DropdownMenuItem(
    text: String,
    onItemClicked: () -> Unit
) {
    DropdownMenuItem(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSecondary,
                shape = CutCornerShape(5.dp)
            ),
        text = {
            Text(
                text = text,
                textAlign = TextAlign.Center
            )
        },
        onClick = onItemClicked
    )
}

//@Preview
//@Composable
//fun CenterAlignedTopAppbarPreview() {
//    CenterAlignedTopAppbar(title = "Words", {}, {}, {}, viewModel())
//}
//
//@Preview
//@Composable
//fun CenterAlignedTopAppbarPreviewDark() {
//    MyLangsheetTheme(useDarkTheme = true) {
//        CenterAlignedTopAppbar(title = "Words", {}, {}, {}, viewModel())
//    }
//}