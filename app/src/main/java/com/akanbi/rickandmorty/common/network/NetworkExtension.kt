package com.akanbi.rickandmorty.common.network

import com.akanbi.rickandmorty.network.ResponseError
import retrofit2.HttpException

suspend fun <T : Any> safeApiCall(call: suspend () -> T?): ResultType<T> = try {
    val dataFromRemote = call()
    if (dataFromRemote == null) {
        ResultType.Fail(ResponseError(Throwable("Request Error")))
    } else {
        ResultType.Success(dataFromRemote)
    }
} catch (exception: Throwable) {
    if (exception is HttpException) {
        ResultType.Fail(ResponseError(exception, exception.code()))
    } else {
        ResultType.Fail(ResponseError(exception))
    }
}

sealed class ResultType<out T> {
    data class Success<out T>(val data: T) : ResultType<T>()
    data class Fail(val exception: ResponseError) : ResultType<Nothing>()
}

fun <T> ResultType<T>.handleResultType(
    success: (T) -> Unit,
    error: (ResponseError) -> Unit
) {
    if (this is ResultType.Success) success(data)
    else if (this is ResultType.Fail) error(exception)
}