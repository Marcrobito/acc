package dev.eighteentech.acc.repository

import dev.eighteentech.acc.entity.CardHolder
import dev.eighteentech.acc.entity.Response
import dev.eighteentech.acc.entity.SimplifiedCard

interface Repository {
    suspend fun fetchHome():Response<List<SimplifiedCard>>
}