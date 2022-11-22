package com.chyrta.livesport.common.data.remote.model

import io.ktor.utils.io.errors.IOException
import kotlinx.serialization.SerializationException

sealed class ApiResponse<out T, out E> {
    data class Success<T>(val body: T): ApiResponse<T, Nothing>()
    sealed class Error<E>: ApiResponse<Nothing, E>() {
        data class HttpError<E>(val statusCode: Int, val errorBody: E?): Error<E>()
        data class NetworkError(val e: IOException): Error<Nothing>()
        data class GenericError(val e: Exception): Error<Nothing>()
    }
}
