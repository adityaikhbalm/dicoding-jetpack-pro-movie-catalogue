package com.dicoding.bajp.data

import com.dicoding.bajp.data.local.LocalRepository
import com.dicoding.bajp.data.local.model.Favorite
import com.dicoding.bajp.data.remote.RemoteRepository

class Repository(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
) : RepositoryDataSource {

    override suspend fun fetchData(type: String) =
        remoteRepository.fetchData(type)

    override suspend fun insertFavorite(favorite: Favorite) =
        localRepository.insertFavorite(favorite)

    override suspend fun deleteFavorite(favorite: Favorite) =
        localRepository.deleteFavorite(favorite)

    override fun getAllFavorite(type: String) =
        localRepository.getAllFavorite(type)

    override fun isFavorite(id: Long) =
        localRepository.getFavorite(id)
}