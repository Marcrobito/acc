package dev.eighteentech.acc.entity

import java.lang.Exception

sealed class Response<out T> {
    object NotLoaded : Response<Nothing>()
    object Loading : Response<Nothing>()
    data class Success<out T>(val data:T) : Response<T>()
    data class Error(val data:Exception) : Response<Nothing>()
}