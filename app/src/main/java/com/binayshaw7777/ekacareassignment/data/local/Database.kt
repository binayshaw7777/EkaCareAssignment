package com.binayshaw7777.ekacareassignment.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.binayshaw7777.ekacareassignment.data.local.dao.ArticleDao
import com.binayshaw7777.ekacareassignment.data.local.model.ArticleEntity
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [ArticleEntity::class], version = 3)
abstract class MyDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao

    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context: Context): MyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "my_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}