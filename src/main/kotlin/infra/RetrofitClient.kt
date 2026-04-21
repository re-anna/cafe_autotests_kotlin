package org.example.infra

import okhttp3.OkHttpClient
import java.time.Duration

object RetrofitClient {

    private val timeout = Duration.ofSeconds(10)
    private val client = OkHttpClient.Builder()
        .retryOnConnectionFailure(true)
        .callTimeout(timeout)
        .readTimeout(timeout)
        .writeTimeout(timeout)
        .addInterceptor { chain: Interceptor.Chain ->
            val builder = chain.request().newBuilder()
            chain.proceed(builder.build())
        }
        .addInterceptor(AllureOkHttp3())
        .build()

    fun <T> createService(service: Class<T>): T =
        Retrofit.Builder()
            .baseUrl(Config.get.backendUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(service)
}
