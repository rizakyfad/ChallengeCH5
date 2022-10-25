package com.rizaki.challengech6Binar.database.remote.retrofit

import com.rizaki.challengech6Binar.service.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    fun getMovie(
        @Query("api_key") apiKey: String
    ): Call<MovieResponse>
}