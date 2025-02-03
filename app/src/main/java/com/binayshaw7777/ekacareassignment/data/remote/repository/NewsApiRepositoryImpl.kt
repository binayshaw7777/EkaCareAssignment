package com.binayshaw7777.ekacareassignment.data.remote.repository

import com.binayshaw7777.ekacareassignment.data.remote.ApiInterface
import com.binayshaw7777.ekacareassignment.data.remote.network.handleApiFlow
import com.binayshaw7777.ekacareassignment.data.remote.response.NewsResponse
import com.binayshaw7777.ekacareassignment.domain.repository.NewsApiRepository
import com.binayshaw7777.ekacareassignment.data.remote.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsApiRepositoryImpl @Inject constructor(
    private val apiService: ApiInterface
) : NewsApiRepository {
    override suspend fun getNewsByQuery(query: String): Flow<NetworkResult<NewsResponse>> {

        return handleApiFlow { apiService.getNews(query = query) }
    }
}