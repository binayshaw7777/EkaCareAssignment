package com.binayshaw7777.ekacareassignment.data.remote

import com.binayshaw7777.ekacareassignment.data.remote.response.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("everything")
    suspend fun getNews(
        @Query("q") query: String,
//        @Query("searchIn") searchIn: String? = null,
//        @Query("sources") sources: String? = null,
//        @Query("domains") domains: String? = null,
//        @Query("excludeDomains") excludeDomains: String? = null,
//        @Query("from") from: String? = null,
//        @Query("to") to: String? = null,
//        @Query("language") language: String? = null,
//        @Query("sortBy") sortBy: String? = "publishedAt",
//        @Query("pageSize") pageSize: Int? = 100,
//        @Query("page") page: Int? = 1
    ): Response<NewsResponse>
}