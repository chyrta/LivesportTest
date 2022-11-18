package com.chyrta.livesport.common.data.remote.model

import io.ktor.utils.io.errors.IOException

sealed interface ApiResult<out T, out E> {
    data class Success<out T>(val value: T): ApiResult<T, Nothing>
    sealed interface Failure<E>: ApiResult<Nothing, E> {
        data class NetworkFailure(val error: IOException): Failure<Nothing>
        data class UnknownFailure(val error: Throwable): Failure<Nothing>
        data class HttpFailure<E>(val code: Int, val error: E?): Failure<E>
        data class ApiFailure<E>(val error: E?): Failure<E>
    }
}
