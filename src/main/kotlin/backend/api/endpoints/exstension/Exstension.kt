package backend.api.endpoints.exstension

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.kotest.assertions.fail
import io.qameta.allure.Step
import retrofit2.Response

class Exstension {
    companion object {

        @Step("Check if response is successful")
        fun <T> Response<T>.checkIsSuccessful(): Response<T> {
            if (!isSuccessful) fail("Response is not successful: code=${code()} error=${errorBody()?.string()}")
            return this
        }

        @Step("Error body as object or type <T>")
        fun <T> Response<T>.getAsObject(): T =
            body() ?: fail("Response body is null. code=${code()} error=${errorBody()?.string()}")

        @Step("Error body as object or type <R>")
        inline fun <reified R> Response<*>.getErrorAsObject(): R {
            val raw = errorBody()?.string().orEmpty()
            if (raw.isBlank()) fail("Error body is blank. code=${code()}")
            return jacksonObjectMapper().readValue<R>(raw)
        }

        @Step("Get error body as string")
        inline fun <reified T> Response<T>.getErrorBody(): String {
            return errorBody()?.string() ?: ""
        }

        //Nice to know
        /*        inline fun <reified T> Call<T>.retryIfEmpty(count: Int): Response<T> {
                    var i = 1
                    var res: Response<T>
                    do {
                        res = this.clone().execute()
                        if ((res.getAsObject() as List<*>).isNotEmpty()) break
                        if ( i == count) fail("Request body is empty") else i++
                    } while ((res.getAsObject() as List<*>).isNotEmpty())
                    return res
                }*/

        @Step("String to Bearer token")
        fun String.toBearer(): String {
            return "Bearer $this"
        }
    }
}