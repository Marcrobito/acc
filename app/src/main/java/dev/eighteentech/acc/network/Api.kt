package dev.eighteentech.acc.network

import dev.eighteentech.acc.entity.Home
import retrofit2.http.GET

interface Api {
    @GET("home")
    suspend fun getHome():Home?
}

