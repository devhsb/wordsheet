package com.hasib.mylangsheet.ui.shared_components

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Environment
import android.provider.Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.MoreVert
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.hasib.mylangsheet.data.room.backup.BackupDatabase.backupDb
import com.hasib.mylangsheet.data.room.backup.RestoreDatabase.restoreDb
import com.hasib.mylangsheet.ui.screens.words.wordmain.WordViewModel

@RequiresApi(Build.VERSION_CODES.N)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterAlignedTopAppbar(
    title: String,
    onWordItemClicked: () -> Unit,
    onPracticeItemClicked: () -> Unit,
    onCategoryItemClicked: () -> Unit,
    wordViewModel: WordViewModel
) {
    val context = LocalContext.current

    val wordUiState by wordViewModel.wordUiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        var menuExpanded by wordViewModel.topbarDropDownState

        var backupRestoreMenuExtended by remember { mutableStateOf(false) }

        CenterAlignedTopAppBar(title = {
            if (wordUiState.isSearchActive) {
                SearchTextField(
                    wordViewModel = wordViewModel
                )
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .clickable { menuExpanded = !menuExpanded },
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
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

                    IconButton(onClick = {
                        backupRestoreMenuExtended = !backupRestoreMenuExtended
                    }) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = null
                        )
                    }
                }
            }
        })



        DropdownMenu(
            modifier = Modifier
                .fillMaxWidth(),
            expanded = menuExpanded,
            onDismissRequest = { menuExpanded = !menuExpanded }
        ) {
            Column {
                AppbarDropdownMenuItem(
                    text = "Words",
                    onItemClicked = onWordItemClicked
                )
                AppbarDropdownMenuItem(
                    text = "Practice",
                    onItemClicked = onPracticeItemClicked
                )
                AppbarDropdownMenuItem(
                    text = "Categories",
                    onItemClicked = onCategoryItemClicked
                )
            }
        }

        Box(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .align(Alignment.End),
        ) {
            DropdownMenu(
                expanded = backupRestoreMenuExtended,
                onDismissRequest = { backupRestoreMenuExtended = !backupRestoreMenuExtended }
            ) {

                BackupRestoreDropdownItem(
                    text = "Backup",
                    onItemClicked = {
                        if (!Environment.isExternalStorageManager()) {
                            Toast.makeText(context, "Please grant permission", Toast.LENGTH_SHORT)
                                .show()
                            val openSettings = Intent(ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION)
                            context.startActivity(openSettings)
                        } else {
                            backupDb(context = context)
                        }

                        backupRestoreMenuExtended = !backupRestoreMenuExtended
                    }
                )

                BackupRestoreDropdownItem(
                    text = "Restore",
                    onItemClicked = {

                        if (!Environment.isExternalStorageManager()) {
                            Toast.makeText(context, "Please grant permission", Toast.LENGTH_SHORT)
                                .show()
                            val openSettings = Intent(ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION)
                            context.startActivity(openSettings)
                        } else {
                            restoreDb(context = context)
                            restartApp(context = context)
                        }

                        backupRestoreMenuExtended = !backupRestoreMenuExtended
                    }
                )
            }
        }
    }
}

fun restartApp(context: Context) {
    val intent = context.packageManager.getLaunchIntentForPackage(context.packageName)
    intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
    context.startActivity(intent)
    Runtime.getRuntime().exit(0) // Optional: Use this line to forcefully close the current process
}

@Composable
private fun AppbarDropdownMenuItem(
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


@Composable
private fun BackupRestoreDropdownItem(
    text: String,
    onItemClicked: () -> Unit
) {
    DropdownMenuItem(
        text = {
            Text(text = text)
        },
        onClick = onItemClicked
    )
}