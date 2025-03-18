package com.chetan.movieecho.common

sealed class ApiResult<T> (val data : T? = null, val message : String? = null, val throwable: Throwable? = null){
    class Loading<T>(data: T? = null) : ApiResult<T>(data)
    class Success<T>(data: T?) : ApiResult<T>(data)
    class Error<T>(message: String, throwable: Throwable? = null, data: T? = null) : ApiResult<T>(data, message, throwable)
}