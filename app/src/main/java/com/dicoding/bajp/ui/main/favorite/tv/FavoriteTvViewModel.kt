package com.dicoding.bajp.ui.main.favorite.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.bajp.data.Repository
import com.dicoding.bajp.data.local.model.Favorite
import kotlinx.coroutines.launch

class FavoriteTvViewModel(private val repository: Repository) : ViewModel() {

    lateinit var fetchFavoriteTv: LiveData<PagedList<Favorite>>

    fun favoriteTv() {
        fetchFavoriteTv = LivePagedListBuilder(
            repository.getAllFavorite("tv"),
            5
        ).build()
    }

    fun deleteFavorite(favorite: Favorite) {
        viewModelScope.launch {
            repository.deleteFavorite(favorite)
        }
    }
}