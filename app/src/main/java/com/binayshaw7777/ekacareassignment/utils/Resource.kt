package com.binayshaw7777.ekacareassignment.utils

sealed class NetworkResult<T> {
    class Success<T: Any>(val data: T) : NetworkResult<T>()
    class Error<T: Any>(val code: Int, val message: String?) : NetworkResult<T>()
    class Exception<T: Any>(val e: Throwable) : NetworkResult<T>()
    class Loading<T : Any> : NetworkResult<T>()
    class Clear<T>: NetworkResult<T>()
}