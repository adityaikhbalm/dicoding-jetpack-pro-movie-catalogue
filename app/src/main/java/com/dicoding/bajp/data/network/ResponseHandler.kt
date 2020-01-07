package com.dicoding.bajp.data.network

import com.dicoding.bajp.R
import retrofit2.HttpException
import java.net.SocketTimeoutException

open class ResponseHandler {

    fun <T : Any> handleSuccess(data: T?): Resource<T> {
        return Resource.success(data)
    }

    fun <T : Any> handleException(e: Exception?): Resource<T> {
        return when (e) {
            is HttpException -> Resource.error(getErrorMessage(e.code()), null)
            is SocketTimeoutException -> Resource.error(
                getErrorMessage(ErrorCodes.SocketTimeOut.code),
                null
            )
            else -> Resource.error(getErrorMessage(Int.MAX_VALUE), null)
        }
    }

    private fun getErrorMessage(code: Int): Int {
        return when (code) {
            ErrorCodes.SocketTimeOut.code -> R.string.timeout
            else -> R.string.no_internet
        }
    }
}