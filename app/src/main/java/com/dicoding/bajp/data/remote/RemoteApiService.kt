package com.dicoding.bajp.data.remote

import com.dicoding.bajp.BuildConfig
import com.dicoding.bajp.data.remote.response.ApiResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteApiService {
    companion object {
        operator fun invoke(): RemoteApiService {
            val requestInterceptor = Interceptor { chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("api_key", BuildConfig.API_KEY)
                    .build()

                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RemoteApiService::class.java)
        }
    }

    @GET("discover/{type}")
    suspend fun getDiscoverAsync(
        @Path("type") type: String
    ): ApiResponse
}