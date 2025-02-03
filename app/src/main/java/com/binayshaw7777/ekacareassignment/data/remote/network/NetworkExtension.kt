package com.binayshaw7777.ekacareassignment.data.remote.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response

fun <T : Any> handleApiFlow(
    execute: suspend () -> Response<T>
): Flow<NetworkResult<T>> = flow {
    try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            emit(NetworkResult.Success(body))
        } else {
            emit(NetworkResult.Error(response.code(), response.message()))  // Emit error state
        }
    } catch (e: HttpException) {
        emit(NetworkResult.Error(e.code(), e.message()))
    } catch (e: Throwable) {
        emit(NetworkResult.Exception(e))
    }
}
