package com.dicoding.bajp.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.bajp.data.Repository
import com.dicoding.bajp.ui.detail.DetailViewModel
import com.dicoding.bajp.ui.main.favorite.movie.FavoriteMovieViewModel
import com.dicoding.bajp.ui.main.favorite.tv.FavoriteTvViewModel
import com.dicoding.bajp.ui.main.movie.MovieViewModel
import com.dicoding.bajp.ui.main.tv.TvViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        operator fun invoke(context: Context) =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return with(modelClass) {
            when {
                isAssignableFrom(DetailViewModel::class.java) -> DetailViewModel(repository)
                isAssignableFrom(MovieViewModel::class.java) -> MovieViewModel(repository)
                isAssignableFrom(TvViewModel::class.java) -> TvViewModel(repository)
                isAssignableFrom(FavoriteMovieViewModel::class.java) -> FavoriteMovieViewModel(
                    repository
                )
                isAssignableFrom(FavoriteTvViewModel::class.java) -> FavoriteTvViewModel(repository)
                else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
    }

}