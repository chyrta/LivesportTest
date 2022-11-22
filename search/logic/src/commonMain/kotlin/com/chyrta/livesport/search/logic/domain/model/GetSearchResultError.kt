package com.chyrta.livesport.search.logic.domain.model

sealed interface GetSearchResultError {
    object InvalidRequestParameters : GetSearchResultError
    object MissingRequestParameters : GetSearchResultError
    object NetworkError : GetSearchResultError
    data class HttpError(val statusCode: Int) : GetSearchResultError
    object ServiceUnavailable : GetSearchResultError
    object UnknownError : GetSearchResultError
}
