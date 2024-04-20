package com.hasib.mylangsheet.ui.shared_components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BottomBar(
    onAddButtonClicked: () -> Unit,
    addBtnText: String
) {
    BottomBarContent(
        onAddButtonClicked = onAddButtonClicked,
        addBtnText = addBtnText
    )
}

@Composable
private fun BottomBarContent(
    onAddButtonClicked: () -> Unit,
    addBtnText: String
) {
    BottomAppBar(
        modifier = Modifier
            .clip(
                RoundedCornerShape(
                    topStart = 35.dp,
                    topEnd = 35.dp
                )
            ),
        containerColor = MaterialTheme.colorScheme.surfaceVariant
    ) {
        AddButton(
            onAddButtonClicked = onAddButtonClicked,
            addBtnText = addBtnText
        )
    }
}

@Composable
private fun AddButton(
    onAddButtonClicked: () -> Unit,
    addBtnText: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp),
        contentAlignment = Alignment.CenterEnd
    ) {

        ExtendedFloatingActionButton(
            modifier = Modifier.border(
                1.dp,
                MaterialTheme.colorScheme.primary,
                RoundedCornerShape(20.dp)
            ),
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            elevation = FloatingActionButtonDefaults.elevation(
                defaultElevation = 7.dp
            ),
            onClick = onAddButtonClicked,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = addBtnText, color = MaterialTheme.colorScheme.onPrimaryContainer)
            }
        }

    }
}

@Preview()
@Composable
private fun BottomBarPreview() {
    BottomBar(onAddButtonClicked = { }, "ADD")
}