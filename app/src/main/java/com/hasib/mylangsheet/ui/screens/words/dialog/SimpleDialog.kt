package com.hasib.mylangsheet.ui.screens.words.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun SimpleDialog(
    modifier: Modifier = Modifier,
    isOpened: () -> Unit,
    onDeletePressed: () -> Unit,
    onEditPressed: () -> Unit,
    wordMeaning: String
) {

    val width = LocalConfiguration.current.screenWidthDp

    Dialog(onDismissRequest = isOpened) {
        Column(
            modifier = modifier
                .width(
                    (width / 1.2).dp
                )
                .background(MaterialTheme.colorScheme.onBackground)
                .padding(10.dp)
                .clip(shape = RoundedCornerShape(20.dp)),

            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = wordMeaning,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )

            Row(
                modifier = Modifier
                    .align(Alignment.End),
            ) {

                IconButton(
                    onClick = onEditPressed,
                ) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }

                IconButton(
                    onClick = onDeletePressed,
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SimpleDialogPreview() {
    SimpleDialog(
        isOpened = { },
        onDeletePressed = {},
        onEditPressed = {},
        wordMeaning = "Sample "
    )
}














