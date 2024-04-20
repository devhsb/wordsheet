package com.hasib.mylangsheet.ui.screens.categories.catdialog

import android.widget.Toast
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun CatDialog() {
    Dialog(onDismissRequest = { /*TODO*/ }) {
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
                text = "New Category",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )

            Spacer(modifier = Modifier.height(20.dp))

            NewCategoryTextField(
                onValueChange = {},
                value = ""
            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                onClick = { }
            ) {
                Text(text = "Done", color = Color.White)
            }
        }
    }
}

@Composable
private fun NewCategoryTextField(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    value: String
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text("Category Name") },
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


@Composable
@Preview
private fun CatDialogPreview() {
    CatDialog()
}



















