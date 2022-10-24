package com.rizaki.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Response
import androidx.lifecycle.ViewModel
import com.rizaki.challengech5.service.ApiConfig
import com.rizaki.challengech5.service.Movie
import com.rizaki.challengech5.service.MovieResponse
import retrofit2.Call



class MovieViewModel : ViewModel() {

    private val _movie = MutableLiveData<List<Movie>>()
    val movie: LiveData<List<Movie>> = _movie

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "MainViewModel"
        private const val API_KEY = "374186e4dc9f4f0eb552ea6c3fd9e141"
    }

    init {
        fetchData()
    }

    private fun fetchData() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getMovie(API_KEY)
        client.enqueue(object : retrofit2.Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _movie.value = response.body()?.results
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

}