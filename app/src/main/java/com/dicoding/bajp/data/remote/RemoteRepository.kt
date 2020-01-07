package com.dicoding.bajp.data.remote

import com.dicoding.bajp.data.network.Resource
import com.dicoding.bajp.data.network.ResponseHandler
import com.dicoding.bajp.data.remote.response.ApiResponse
import com.dicoding.bajp.utlis.EspressoIdlingResource
import java.io.IOException

class RemoteRepository(
    private val remoteApiService: RemoteApiService,
    private val responseHandler: ResponseHandler
) {

    suspend fun fetchData(type: String): Resource<ApiResponse> {
        EspressoIdlingResource.increment()
        return try {
            val data = remoteApiService.getDiscoverAsync(type)
            EspressoIdlingResource.decrement()
            return responseHandler.handleSuccess(data)
        } catch (e: IOException) {
            responseHandler.handleException(e)
        }
    }
}