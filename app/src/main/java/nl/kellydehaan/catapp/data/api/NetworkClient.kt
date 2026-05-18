package nl.kellydehaan.catapp.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.java

// TODO: save URL and API_KEY in a config file. In case of the API_KEY, consider securing or obfuscating
object NetworkClient {
    private const val BASE_URL = "https://api.thecatapi.com/v1/"

    private const val API_KEY = "live_pYlQ0xyEUcocSqtXDkAPYKcnr7Hl43KjS1sQEarqP9fnb32j1vyYGLouHHCwZJgy"

    // TODO: only add a logging interceptor during development
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("x-api-key",
                    API_KEY)
                .build()
            chain.proceed(request)
        }
        .build()

    val catApiService: CatApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CatApiService::class.java)
}