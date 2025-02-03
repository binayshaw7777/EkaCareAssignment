package com.binayshaw7777.ekacareassignment.di

import android.content.Context
import com.binayshaw7777.ekacareassignment.EkaCareAssignmentApp
import com.binayshaw7777.ekacareassignment.data.local.datastore.DataStoreUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    fun provideContext(@ApplicationContext app: EkaCareAssignmentApp) : Context = app

    @Provides
    fun provideDataStoreUtil(@ApplicationContext context: Context): DataStoreUtil = DataStoreUtil(context)

}