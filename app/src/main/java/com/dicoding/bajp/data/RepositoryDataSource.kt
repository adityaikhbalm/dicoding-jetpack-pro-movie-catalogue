package com.dicoding.bajp.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.dicoding.bajp.data.local.model.Favorite
import com.dicoding.bajp.data.network.Resource
import com.dicoding.bajp.data.remote.response.ApiResponse

interface RepositoryDataSource {

    suspend fun fetchData(type: String): Resource<ApiResponse>

    suspend fun insertFavorite(favorite: Favorite)

    suspend fun deleteFavorite(favorite: Favorite)

    fun getAllFavorite(type: String): DataSource.Factory<Int, Favorite>

    fun isFavorite(id: Long): LiveData<Favorite>
}