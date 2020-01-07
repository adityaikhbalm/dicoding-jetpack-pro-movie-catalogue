package com.dicoding.bajp.ui.main.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.bajp.data.Repository
import com.dicoding.bajp.data.network.Resource
import com.dicoding.bajp.data.remote.response.ApiResponse
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: Repository) : ViewModel() {

    private val _fetchMovie = MutableLiveData<Resource<ApiResponse>>()
    val fetchMovie: LiveData<Resource<ApiResponse>>
        get() = _fetchMovie

    fun allMovies() {
        viewModelScope.launch {
            Resource.loading(null)
            _fetchMovie.value = repository.fetchData("movie")
        }
    }
}