package com.rizaki.challengech5.ui.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Response
import com.rizaki.challengech5.model.Result
import androidx.lifecycle.ViewModel
import com.rizaki.challengech5.model.ListMovie
import com.rizaki.challengech5.service.MovieApi
import retrofit2.Call



class MainViewModel: ViewModel() {

    private val movies : MutableLiveData<List<Result>> by lazy {
        MutableLiveData<List<Result>>().also {
            getAllMovies()
        }
    }


    fun getMovies(): LiveData<List<Result>> {
        return movies
    }
    private fun getAllMovies(){
        MovieApi.retrofitService.allMovies().enqueue(object : retrofit2.Callback<ListMovie>{
            override fun onResponse(
                call: Call<ListMovie>,
                response: Response<ListMovie>
            ) {
                movies.value = response.body()?.results
            }

            override fun onFailure(call: Call<ListMovie>, t: Throwable) {
                Log.d("Tag",t.message.toString())
            }

        })
    }
}
