package com.binayshaw7777.ekacareassignment.di

import android.content.Context
import androidx.room.Room
import com.binayshaw7777.ekacareassignment.data.local.MyDatabase
import com.binayshaw7777.ekacareassignment.data.local.dao.ArticleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MyDatabase {
        return Room.databaseBuilder(
            context,
            MyDatabase::class.java,
            "my_database"
        ).build()
    }

    @Provides
    fun provideArticleDao(database: MyDatabase): ArticleDao {
        return database.articleDao()
    }
}