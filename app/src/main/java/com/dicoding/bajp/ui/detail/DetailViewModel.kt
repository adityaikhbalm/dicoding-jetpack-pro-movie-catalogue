package com.dicoding.bajp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.bajp.data.Repository
import com.dicoding.bajp.data.local.model.Favorite
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: Repository) : ViewModel() {

    lateinit var isFavorite: LiveData<Favorite>

    fun fetchFavorite(id: Long) {
        isFavorite = repository.isFavorite(id)
    }

    fun insertFavorite(favorite: Favorite) {
        viewModelScope.launch {
            repository.insertFavorite(favorite)
        }
    }

    fun deleteFavorite(favorite: Favorite) {
        viewModelScope.launch {
            repository.deleteFavorite(favorite)
        }
    }
}