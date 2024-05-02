package com.hasib.mylangsheet.data.room.entites.category

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.hasib.mylangsheet.data.room.LangDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryCallback (private val dbInstance: LangDatabase)  {

        fun categoryInitize() {
            val langDao = dbInstance.getLangDao()
            CoroutineScope(Dispatchers.IO).launch {
                langDao.insertCategory(
                    Category("general")
                )
            }
        }
}