package dev.eighteentech.acc.repository

import dev.eighteentech.acc.entity.CardHolder
import dev.eighteentech.acc.entity.Response
import dev.eighteentech.acc.entity.SimplifiedCard
import dev.eighteentech.acc.network.Api
import java.lang.Exception

class NetworkModel(private val api: Api) {
    suspend fun fetchHome(): Response<List<SimplifiedCard>> {
        return try {
            val home = api.getHome()
            if (home != null) {
                if (home.page.cards.isNotEmpty()) Response.Success(home.page.toSimplifiedCardList())
                else Response.Error(Exception("Something fail"))
            } else Response.Error(Exception("Something fail"))
        } catch (e: Exception) {
            Response.Error(e)
        }
    }
}