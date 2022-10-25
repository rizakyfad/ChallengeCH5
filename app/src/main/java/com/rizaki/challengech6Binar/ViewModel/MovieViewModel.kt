package com.rizaki.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Response
import androidx.lifecycle.ViewModel
import com.rizaki.challengech6Binar.database.remote.retrofit.ApiConfig
import com.rizaki.challengech6Binar.service.Movie
import com.rizaki.challengech6Binar.service.MovieResponse
import retrofit2.Call



class MovieViewModel : ViewModel() {

    private val _movie = MutableLiveData<List<Movie>>()
    val movie: LiveData<List<Movie>> = _movie

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        const val TAG = "MainViewModel"
        const val API_KEY = "374186e4dc9f4f0eb552ea6c3fd9e141"
    }

    init {
        fetchData()
    }

    private fun fetchData() {
        _isLoading.postValue(true)
        val client = ApiConfig.getApiService().getMovie(API_KEY)
        client.enqueue(object : retrofit2.Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                if (response.isSuccessful) {
                    _isLoading.postValue(false)
                    _movie.postValue(response.body()?.results)
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                _isLoading.postValue(false)
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }
}