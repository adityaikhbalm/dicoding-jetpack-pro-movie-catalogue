package com.dicoding.bajp.ui.main.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.bajp.data.Repository
import com.dicoding.bajp.data.network.Resource
import com.dicoding.bajp.data.remote.response.ApiResponse
import kotlinx.coroutines.launch

class TvViewModel(private val repository: Repository) : ViewModel() {

    private val _fetchTv = MutableLiveData<Resource<ApiResponse>>()
    val fetchTv: LiveData<Resource<ApiResponse>>
        get() = _fetchTv

    fun allTvShows() {
        viewModelScope.launch {
            Resource.loading(null)
            _fetchTv.value = repository.fetchData("tv")
        }
    }
}