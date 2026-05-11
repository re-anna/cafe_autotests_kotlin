package backend.api.extension

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.kotest.assertions.fail
import retrofit2.Response

object ResponseExt {

    fun <T> Response<T>.checkIsSuccessful(): Response<T> {
        if (!isSuccessful) fail("Response is not successful: code=${code()} error=${errorBody()?.string()}")
        return this
    }

    fun <T> Response<T>.getAsObject(): T =
        body() ?: fail("Response body is null. code=${code()} error=${errorBody()?.string()}")

    inline fun <reified R> Response<*>.getErrorAsObject(): R {
        val raw = errorBody()?.string().orEmpty()
        if (raw.isBlank()) fail("Error body is blank. code=${code()}")
        return jacksonObjectMapper().readValue<R>(raw)
    }

    fun String.toBearer(): String = "Bearer $this"
}
