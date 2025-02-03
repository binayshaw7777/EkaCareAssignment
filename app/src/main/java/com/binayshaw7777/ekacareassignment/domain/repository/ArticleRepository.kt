package com.binayshaw7777.ekacareassignment.domain.repository

import com.binayshaw7777.ekacareassignment.data.local.model.ArticleEntity
import com.binayshaw7777.ekacareassignment.data.remote.response.Article
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    suspend fun saveArticle(article: Article) : Long

    fun getArticles(): Flow<List<ArticleEntity>>

    suspend fun deleteArticle(articleEntity: ArticleEntity) : Boolean
}