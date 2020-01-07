package com.dicoding.bajp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.dicoding.bajp.data.local.model.Favorite

@Dao
interface FavoriteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite)

    @Delete
    suspend fun deleteFavorite(favorite: Favorite)

    @Query("SELECT * FROM favorite WHERE type = :type")
    fun getAllFavorite(type: String): DataSource.Factory<Int, Favorite>

    @Query("SELECT * FROM favorite WHERE id = :id")
    fun isFavorite(id: Long): LiveData<Favorite>
}