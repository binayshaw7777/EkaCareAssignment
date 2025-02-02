package com.binayshaw7777.ekacareassignment.di

import com.binayshaw7777.ekacareassignment.data.remote.repository.NewsApiRepositoryImpl
import com.binayshaw7777.ekacareassignment.domain.repository.NewsApiRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class RepositoryModule {

    @ActivityRetainedScoped
    @Binds
    abstract fun bindNewsApiRepository(
        repository: NewsApiRepositoryImpl
    ): NewsApiRepository
}