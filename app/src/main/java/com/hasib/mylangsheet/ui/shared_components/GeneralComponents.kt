package com.hasib.mylangsheet.ui.shared_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

//RED BACKGROUND
@Composable
fun RedBackground(degree: Float) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
            .background(
                MaterialTheme.colorScheme.errorContainer,
                shape = MaterialTheme.shapes.medium
            ),

        contentAlignment = Alignment.CenterEnd
    ) {
            Icon(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .rotate(degree),
                imageVector = Icons.Filled.Delete,
                contentDescription = null,
                tint = Color.White
            )
    }
}