package com.hasib.mylangsheet.ui.shared_components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Refresh
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BottomBar(
    onAddButtonClicked: () -> Unit,
    onPracticeBtnClicked: () -> Unit,
    btnText: String,
) {
    BottomBarContent(
        onAddButtonClicked = onAddButtonClicked,
        onPracticeBtnClicked = onPracticeBtnClicked,
        btnText = btnText
    )
}

@Composable
private fun BottomBarContent(
    onAddButtonClicked: () -> Unit,
    onPracticeBtnClicked: () -> Unit,
    btnText: String,
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

        Row{
            ActionButton(
                modifier = Modifier.weight(1f),
                onBtnClicked = onPracticeBtnClicked,
                btnText = "Practice",
                icon = Icons.Outlined.Refresh
            )

            ActionButton(
                onBtnClicked = onAddButtonClicked,
                btnText = btnText,
            )
        }

    }
}

@Composable
private fun ActionButton(
    modifier: Modifier = Modifier,
    onBtnClicked: () -> Unit,
    btnText: String,
    icon: ImageVector = Icons.Filled.Add
) {
    Box(
        modifier = modifier
            .padding(horizontal = 10.dp)
            .border(
                1.dp,
                MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(20.dp)
            )
    ) {

        ExtendedFloatingActionButton(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            elevation = FloatingActionButtonDefaults.elevation(
                defaultElevation = 0.dp
            ),
            onClick = onBtnClicked,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = btnText, color = MaterialTheme.colorScheme.onPrimaryContainer)
            }
        }

    }
}

@Preview(showSystemUi = true)
@Composable
private fun BottomBarPreview() {
    BottomBar({}, {}, "Add")
}