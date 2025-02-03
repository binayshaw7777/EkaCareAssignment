package com.binayshaw7777.ekacareassignment.data.local.repository

import com.binayshaw7777.ekacareassignment.data.local.dao.ArticleDao
import com.binayshaw7777.ekacareassignment.data.local.model.ArticleEntity
import com.binayshaw7777.ekacareassignment.data.remote.response.Article
import com.binayshaw7777.ekacareassignment.domain.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val articleDao: ArticleDao
) : ArticleRepository {

    override suspend fun saveArticle(article: Article): Long {
        val toSave = ArticleEntity(
            title = article.title,
            description = article.description,
            url = article.url,
            urlToImage = article.urlToImage
        )
        return articleDao.insertArticle(toSave)
    }

    override fun getArticles(): Flow<List<ArticleEntity>>{
        return articleDao.getSavedArticles()
    }

    override suspend fun deleteArticle(articleEntity: ArticleEntity): Boolean {
        return articleDao.deleteArticle(articleEntity) > 0
    }
}