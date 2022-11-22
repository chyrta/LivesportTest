package com.chyrta.livesport.search.logic.presentation.generator

import com.chyrta.livesport.search.logic.presentation.generator.LivesportApiMock.Parameters.QUERY
import com.chyrta.livesport.search.logic.presentation.generator.LivesportApiMock.Parameters.SPORT_IDS
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.MockRequestHandleScope
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.HttpResponseData
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel

class LivesportApiMock {

    val engine = MockEngine { request ->
        handleSearchRequest(request) ?: genericErrorResponse()
    }

    private fun MockRequestHandleScope.handleSearchRequest(request: HttpRequestData): HttpResponseData? {
        val searchQueryParameter: String? = request.url.parameters[QUERY]
        val sportsParameters: String? = request.url.parameters[SPORT_IDS]

        if (searchQueryParameter.orEmpty().length < 2) {
            return invalidParametersErrorResponse()
        }

        if (sportsParameters.isNullOrEmpty()) {
            return missingParametersErrorResponse()
        }

        return respond(
            content = ByteReadChannel(successResponse),
            status = HttpStatusCode.OK,
            headers = headersOf(HttpHeaders.ContentType, "application/json")
        )
    }

    private fun MockRequestHandleScope.invalidParametersErrorResponse(): HttpResponseData {
        return respond(
            content = ByteReadChannel(invalidParametersError),
            status = HttpStatusCode.UnprocessableEntity,
            headers = headersOf(HttpHeaders.ContentType, "application/json")
        )
    }

    private fun MockRequestHandleScope.missingParametersErrorResponse(): HttpResponseData {
        return respond(
            content = ByteReadChannel(missingParametersError),
            status = HttpStatusCode.BadRequest,
            headers = headersOf(HttpHeaders.ContentType, "application/json")
        )
    }

    private fun MockRequestHandleScope.genericErrorResponse(): HttpResponseData {
        return respond(
            content = "",
            status = HttpStatusCode.BadRequest,
            headers = headersOf(HttpHeaders.ContentType, "application/json")
        )
    }

    companion object {
        const val successResponse =
            """[{"id":"AZg49Et9","url":"djokovic-novak","gender":{"id":1,"name":"Men"},"name":"Djokovic Novak","type":{"id":3,"name":"Player"},"participantTypes":[{"id":2,"name":"Player"}],"sport":{"id":2,"name":"Tennis"},"favouriteKey":{"web":"2_AZg49Et9","portable":"2_AZg49Et9"},"flagId":null,"defaultCountry":{"id":167,"name":"Serbia"},"images":[{"path":"tSfwGCdM-0rY6MEPI.png","usageId":3,"variantTypeId":15}],"teams":[],"defaultTournament":null,"superTemplate":null}]"""
        const val missingParametersError = """{"code":101,"message":"One or more values","name":"BadRequestError","stack":"BadRequestError","errors":[{"message":""q" is required","type":"any.required"}]}"""
        const val invalidParametersError = """{"code":100,"message":"One or more values invalid","name":"UnprocessableEntityError","stack":"UnprocessableEntityError","errors":[{"message":"message","type":"any.only"}]}"""
        const val serviceUnavailableError = """{"code":110,"message":"Sport API error","name":"ServiceUnavailableError","stack":"ServiceUnavailableError: Sport API errorn ...)","data":{"name":"MissingResponseError"}}"""
    }

    private object Parameters {
        const val QUERY = "q"
        const val SPORT_IDS = "sport-ids"
        const val TYPE_IDS = "type-ids"
        const val PROJECT_ID = "project-id"
        const val LANGUAGE_ID = "lang-id"
        const val PROJECT_TYPE_ID = "project-type-id"
    }
}
