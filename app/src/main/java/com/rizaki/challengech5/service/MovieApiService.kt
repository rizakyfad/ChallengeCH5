package com.rizaki.challengech5.service

import com.rizaki.challengech5.model.ListMovie
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://api.themoviedb.org/"
private const val API_KEY = "374186e4dc9f4f0eb552ea6c3fd9e141"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface MovieApiService{

    @GET("3/movie/634649/recommendations?api_key=374186e4dc9f4f0eb552ea6c3fd9e141&language=en-US&page=1")
    fun allMovies(): Call<ListMovie>


}

object MovieApi{
    private val logging: HttpLoggingInterceptor
        get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            return httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        }
    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    val instance: MovieApiService by lazy{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        retrofit.create(MovieApiService::class.java)
    }
    val retrofitService:MovieApiService by lazy{retrofit.create(MovieApiService::class.java)}
}