package com.dicoding.bajp.data.network

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

enum class ErrorCodes(val code: Int) {
    SocketTimeOut(-1)
}