package com.dicoding.bajp.ui.main.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.bajp.data.Repository
import com.dicoding.bajp.data.local.model.Favorite
import kotlinx.coroutines.launch

class FavoriteMovieViewModel(private val repository: Repository) : ViewModel() {

    lateinit var fetchFavoriteMovie: LiveData<PagedList<Favorite>>

    fun favoriteMovie() {
        fetchFavoriteMovie = LivePagedListBuilder(
            repository.getAllFavorite("movie"),
            5
        ).build()
    }

    fun deleteFavorite(favorite: Favorite) {
        viewModelScope.launch {
            repository.deleteFavorite(favorite)
        }
    }
}