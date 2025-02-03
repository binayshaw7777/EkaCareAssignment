package com.binayshaw7777.ekacareassignment.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.binayshaw7777.ekacareassignment.data.local.model.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: ArticleEntity): Long

    @Query("SELECT * FROM article ORDER BY savedAt DESC")
    fun getSavedArticles(): Flow<List<ArticleEntity>>

    @Delete
    suspend fun deleteArticle(article: ArticleEntity): Int
}