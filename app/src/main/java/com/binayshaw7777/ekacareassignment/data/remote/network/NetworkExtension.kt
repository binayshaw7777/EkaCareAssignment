package com.binayshaw7777.ekacareassignment.data.remote.network

import com.binayshaw7777.ekacareassignment.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response

suspend fun <T : Any> handleApi(
    execute: suspend () -> Response<T>
): NetworkResult<T> {
    return try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            NetworkResult.Success(body)
        } else {
            NetworkResult.Error(code = response.code(), message = response.message())
        }
    } catch (e: HttpException) {
        NetworkResult.Error(code = e.code(), message = e.message())
    } catch (e: Throwable) {
        NetworkResult.Exception(e)
    }
}

suspend fun <T : Any> handleApiFlow(
    execute: suspend () -> Response<T>
): Flow<NetworkResult<T>> = flow {
    emit(NetworkResult.Loading())  // Emit loading state before API call
    try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            emit(NetworkResult.Success(body))  // Emit success state
        } else {
            emit(NetworkResult.Error(response.code(), response.message()))  // Emit error state
        }
    } catch (e: HttpException) {
        emit(NetworkResult.Error(e.code(), e.message()))
    } catch (e: Throwable) {
        emit(NetworkResult.Exception(e))
    }
}
