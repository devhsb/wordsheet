package com.hasib.mylangsheet.di

import android.content.Context
import androidx.room.Room
import com.hasib.mylangsheet.data.room.LangDatabase
import com.hasib.mylangsheet.data.room.entites.category.CategoryCallback
import com.hasib.mylangsheet.data.room.migrations.Migration2to3
import com.hasib.mylangsheet.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        LangDatabase::class.java,
        DATABASE_NAME
    )
        .addMigrations(Migration2to3)
        .build().also { langDb ->
            CategoryCallback.categoryInit(langDb)
        }

    @Singleton
    @Provides
    fun provideDao(database: LangDatabase) = database.getLangDao()

}