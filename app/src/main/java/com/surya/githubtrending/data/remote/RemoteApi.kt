package com.surya.githubtrending.data.remote

import com.surya.githubtrending.BuildConfig
import com.surya.githubtrending.data.local.entities.Trend
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Created by suryamudti on 12/11/2019.
 */
interface RemoteApi {

    @GET("repositories")
    suspend fun getRepositories() : Response<List<Trend>>

    companion object{
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ):RemoteApi{
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RemoteApi::class.java)
        }
    }
}