package com.hasib.mylangsheet.ui.screens.words.dialog

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hasib.mylangsheet.ui.screens.words.wordmain.WordViewModel
import com.hasib.mylangsheet.ui.theme.MyLangsheetTheme

@RequiresApi(Build.VERSION_CODES.N)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun NewWordDialog(
    modifier: Modifier = Modifier,
    wordViewModel: WordViewModel,
) {

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
                text = dialogUiState.dialogTitle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )

            Spacer(modifier = Modifier.height(20.dp))

            val word = dialogUiState.selectedWord
            WordInsertionTextField(
                textFieldPlaceholder = "Word",
                onValueChange = {
                    dialogViewModel.updateDialogState(selectedWord = word.copy(word = it))
                },
                value = dialogUiState.selectedWord.word
            )

            Spacer(modifier = Modifier.height(20.dp))

            WordInsertionTextField(
                modifier = Modifier
                    .align(Alignment.End),
                textFieldPlaceholder = "Meaning",
                onValueChange = {
                    dialogViewModel.updateDialogState(word.copy(wordMeaning = it))
                },
                value = dialogUiState.selectedWord.wordMeaning
            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .align(Alignment.End),
                onClick = {
                    if (
                        dialogUiState.selectedWord.word.isEmpty() ||
                        dialogUiState.selectedWord.wordMeaning.isEmpty()
                    ) {
                        Toast.makeText(context, "please fill the text fields", Toast.LENGTH_SHORT)
                            .show()
                    } else {
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
