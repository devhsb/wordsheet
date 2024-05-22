package com.hasib.mylangsheet.data.room.backup

import android.content.Context
import android.os.Environment
import android.widget.Toast
import com.hasib.mylangsheet.util.Constants.DATABASE_NAME
import java.io.File
import java.io.IOException


object BackupDatabase {
    fun backupDb(context: Context) {
        val dbName = DATABASE_NAME
        val mediaDirectory =
            File(Environment.getExternalStorageDirectory().toString() + "/Android/media/com.wordsheet/backup")

        if(!mediaDirectory.exists()) {
            mediaDirectory.mkdirs()
        }

        val db = context.getDatabasePath(dbName).absolutePath
        val wal = context.getDatabasePath("$dbName-wal").absolutePath
        val shm = context.getDatabasePath("$dbName-shm").absolutePath

        try {
            File(db).copyTo(File(mediaDirectory, "$dbName"), true)
            File(wal).copyTo(File(mediaDirectory, "$dbName-wal"), true)
            File(shm).copyTo(File(mediaDirectory, "$dbName-shm"), true)
            Toast.makeText(context, "Backup successful", Toast.LENGTH_SHORT).show()
        }catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(context, "Failed to backup database", Toast.LENGTH_SHORT).show()
        }

    }
}