package com.binayshaw7777.ekacareassignment.di

import com.binayshaw7777.ekacareassignment.data.local.repository.ArticleRepositoryImpl
import com.binayshaw7777.ekacareassignment.data.remote.repository.NewsApiRepositoryImpl
import com.binayshaw7777.ekacareassignment.domain.repository.ArticleRepository
import com.binayshaw7777.ekacareassignment.domain.repository.NewsApiRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindNewsApiRepository(
        repository: NewsApiRepositoryImpl
    ): NewsApiRepository

    @Binds
    abstract fun bindArticleRepository(
        repository: ArticleRepositoryImpl
    ): ArticleRepository
}