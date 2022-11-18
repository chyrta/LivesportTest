package com.chyrta.livesport.common

import io.ktor.utils.io.errors.IOException

sealed class RepositoryResult<out T, out R> {
    data class Success<out T>(val data: T): RepositoryResult<T, Nothing>()
    sealed class Failure<R>: RepositoryResult<Nothing, R>() {
        data class NetworkFailure(val error: IOException): Failure<Nothing>()
        data class UnknownFailure(val error: Throwable): Failure<Nothing>()
        data class HttpFailure<E>(val code: Int, val error: E?): Failure<E>()
        data class ApiFailure<E>(val error: E?): Failure<E>()
    }
}
