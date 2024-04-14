package com.hasib.mylangsheet.ui.screens.words.dialog

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hasib.mylangsheet.ui.screens.words.wordmain.WordViewModel
import com.hasib.mylangsheet.ui.theme.MyLangsheetTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun NewWordDialog(
    modifier: Modifier = Modifier,
    wordViewModel: WordViewModel,
) {
    val configuration = LocalConfiguration.current
    val height = configuration.screenHeightDp
    val width = configuration.screenWidthDp

    val dialogViewModel = wordViewModel.dialogViewModel
    val dialogUiState by dialogViewModel.dialogUiState.collectAsState()

    val context = LocalContext.current


    Dialog(
        onDismissRequest = { dialogViewModel.resetDialogState() },
    ) {

        Card(
            modifier = Modifier
                .wrapContentSize()
                .background(
                    MaterialTheme.colorScheme.surfaceVariant,
                    shape = MaterialTheme.shapes.large
                )
                .padding(10.dp)
        ) {
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "Add New Word",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )

            Spacer(modifier = Modifier.height(20.dp))

            WordInsertionTextField(
                textFieldPlaceholder = "Word",
                onValueChange = {
                    dialogViewModel.updateDialogState(wordTextFieldValue = it)
                },
                value = dialogUiState.wordTextFieldValue
            )

            Spacer(modifier = Modifier.height(20.dp))

            WordInsertionTextField(
                modifier = Modifier
                    .align(Alignment.End),
                textFieldPlaceholder = "Meaning",
                onValueChange = {
                    dialogViewModel.updateDialogState(meaningTextFieldValue = it)
                },
                value = dialogUiState.meaningTextFieldValue
            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .align(Alignment.End),
                onClick = {
                    if(dialogUiState.wordTextFieldValue.isBlank() || dialogUiState.meaningTextFieldValue.isBlank()) {
                        Toast.makeText(context, "please fill the textfields", Toast.LENGTH_SHORT).show()
                    }else {
                        wordViewModel.handleDatabase()
                        dialogViewModel.isDialogOpen()
                    }

                }
            ) {
                Text(text = "Done", color = Color.White)
            }

        }
    }
}


@Composable
fun WordInsertionTextField(
    modifier: Modifier = Modifier,
    textFieldPlaceholder: String,
    onValueChange: (String) -> Unit,
    value: String
) {
    OutlinedTextField(
        modifier = modifier
            .width(180.dp)
            .height(50.dp),
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(textFieldPlaceholder) },
        shape = RoundedCornerShape(
            topStart = 70F,
            bottomEnd = 70F
        ),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedPlaceholderColor = Color.Gray
        )
    )

}


@Preview()
@Composable
fun NewCoinInsertDialogPreview() {
    MyLangsheetTheme(useDarkTheme = true) {
        NewWordDialog(
            wordViewModel = viewModel(),
        )

    }
}

@Preview()
@Composable
fun NewCoinInsertDialogLightPreview() {
    MyLangsheetTheme(useDarkTheme = false) {
        NewWordDialog(
            wordViewModel = viewModel(),
        )

    }
}