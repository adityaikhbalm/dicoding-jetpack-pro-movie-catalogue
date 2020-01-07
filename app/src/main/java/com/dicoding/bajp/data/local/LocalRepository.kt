package com.dicoding.bajp.data.local

import com.dicoding.bajp.data.local.model.Favorite

class LocalRepository(private val favoriteDatabase: FavoriteDatabase) {

    suspend fun insertFavorite(favorite: Favorite) =
        favoriteDatabase.favoriteDAO().insertFavorite(favorite)

    suspend fun deleteFavorite(favorite: Favorite) =
        favoriteDatabase.favoriteDAO().deleteFavorite(favorite)

    fun getAllFavorite(type: String) =
        favoriteDatabase.favoriteDAO().getAllFavorite(type)

    fun getFavorite(id: Long) =
        favoriteDatabase.favoriteDAO().isFavorite(id)
}