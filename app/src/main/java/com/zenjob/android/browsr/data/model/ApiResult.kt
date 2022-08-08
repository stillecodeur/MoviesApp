package com.zenjob.android.browsr.data.model

sealed class ApiResult<out T:Any> {
    data class Success<out T:Any>(val data:T): ApiResult<T>()
    data class Error<out T:Any>(val errorData: ErrorResponse): ApiResult<T>()
    data class Failure<out T:Any>(val t:Throwable): ApiResult<T>()
}