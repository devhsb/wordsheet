package com.hasib.mylangsheet.data.room.backup

import android.content.Context
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.room.Room
import com.hasib.mylangsheet.data.room.LangDatabase
import com.hasib.mylangsheet.util.Constants.DATABASE_NAME
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

object RestoreDatabase {
    fun restoreDb(context: Context) {
        val dbName = DATABASE_NAME
        val mediaDirectory =
            File(Environment.getExternalStorageDirectory().toString() + "/Android/media/com.wordsheet/backup")

        val dbExternal = "$mediaDirectory/$dbName"
        val walExternal = "$mediaDirectory/$dbName-wal"
        val shmExternal = "$mediaDirectory/$dbName-shm"

        val db = context.getDatabasePath("$dbName").absolutePath
        val wal = context.getDatabasePath("$dbName-wal").absolutePath
        val shm = context.getDatabasePath("$dbName-shm").absolutePath

        try {
            File(dbExternal).copyTo(File(db), true)
            File(walExternal).copyTo(File(wal), true)
            File(shmExternal).copyTo(File(shm), true)
        }catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(context, "Backup file not found", Toast.LENGTH_SHORT).show()
        }

    }
}