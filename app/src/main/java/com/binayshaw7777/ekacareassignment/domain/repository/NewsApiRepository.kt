package com.binayshaw7777.ekacareassignment.domain.repository

import com.binayshaw7777.ekacareassignment.data.remote.response.NewsResponse
import com.binayshaw7777.ekacareassignment.data.remote.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface NewsApiRepository {
    suspend fun getNewsByQuery(query: String): Flow<NetworkResult<NewsResponse>>
}