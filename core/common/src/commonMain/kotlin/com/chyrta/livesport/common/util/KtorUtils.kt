package com.chyrta.livesport.common.util

import com.chyrta.livesport.common.data.remote.model.ApiResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.serialization.JsonConvertException
import io.ktor.utils.io.errors.*
import kotlinx.serialization.SerializationException

suspend inline fun <reified T, reified E> HttpClient.safeRequest(
    block: HttpRequestBuilder.() -> Unit,
): ApiResponse<T, E> =
    try {
        val response = request { block() }
        ApiResponse.Success(response.body())
    } catch (e: ClientRequestException) {
        ApiResponse.Error.HttpError(e.response.status.value, e.errorBody())
    } catch (e: ServerResponseException) {
        ApiResponse.Error.HttpError(e.response.status.value, e.errorBody())
    } catch (e: IOException) {
        ApiResponse.Error.NetworkError(e)
    } catch (e: Exception) {
        ApiResponse.Error.GenericError(e)
    }

suspend inline fun <reified E> ResponseException.errorBody(): E? =
    try {
        response.body()
    } catch (e: SerializationException) {
        null
    } catch (e: JsonConvertException) {
        null
    }
