package com.dicoding.bajp.di

import android.content.Context
import com.dicoding.bajp.data.Repository
import com.dicoding.bajp.data.local.FavoriteDatabase
import com.dicoding.bajp.data.local.LocalRepository
import com.dicoding.bajp.data.network.ResponseHandler
import com.dicoding.bajp.data.remote.RemoteApiService
import com.dicoding.bajp.data.remote.RemoteRepository

class Injection {

    companion object {
        fun provideRepository(context: Context): Repository {
            val handler = ResponseHandler()

            val database = FavoriteDatabase.invoke(context)
            val local = LocalRepository(database)

            val apiService = RemoteApiService()
            val remote = RemoteRepository(apiService, handler)
            return Repository(local, remote)
        }
    }
}