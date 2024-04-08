package com.hasib.mylangsheet.ui.shared_components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.hasib.mylangsheet.ui.theme.MyLangsheetTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterAlignedTopAppbar(
    title: String
) {
    CenterAlignedTopAppBar(title = {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
    })
}

@Preview
@Composable
fun CenterAlignedTopAppbarPreview(){
    CenterAlignedTopAppbar(title = "Words")
}

@Preview
@Composable
fun CenterAlignedTopAppbarPreviewDark(){
    MyLangsheetTheme(useDarkTheme = true) {
        CenterAlignedTopAppbar(title = "Words")
    }
}