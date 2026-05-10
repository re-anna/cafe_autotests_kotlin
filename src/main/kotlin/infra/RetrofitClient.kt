package infra

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import config.Config
import io.qameta.allure.okhttp3.AllureOkHttp3
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.time.Duration

object RetrofitClient {

    private val timeout = Duration.ofSeconds(10)

    private val mapper = jacksonObjectMapper()
        .configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    private val client: OkHttpClient = OkHttpClient.Builder()
        .retryOnConnectionFailure(true)
        .callTimeout(timeout)
        .connectTimeout(timeout)
        .readTimeout(timeout)
        .writeTimeout(timeout)
        .addInterceptor(AllureOkHttp3())
        .build()

    fun <T> createService(service: Class<T>): T =
        Retrofit.Builder()
            .baseUrl(Config.get.backendUrl)
            .client(client)
            .addConverterFactory(JacksonConverterFactory.create(mapper))
            .build()
            .create(service)
}