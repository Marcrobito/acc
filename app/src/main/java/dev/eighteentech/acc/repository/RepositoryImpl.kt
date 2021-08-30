package dev.eighteentech.acc.repository
import dev.eighteentech.acc.App
import dev.eighteentech.acc.entity.Response
import dev.eighteentech.acc.entity.Response.Success
import dev.eighteentech.acc.entity.Response.Error
import dev.eighteentech.acc.entity.SimplifiedCard
import dev.eighteentech.acc.room.CardDao
import java.lang.Exception

class RepositoryImpl(private val network: NetworkModel, private val db: CardDao) : Repository {

    override suspend fun fetchHome(): Response<List<SimplifiedCard>> {
        if (App.hasInternet() == true) {
            val result = network.fetchHome()
            if(result is Success)
                storeData(result.data)
            return result
        }

        val result = db.getAll()
        return if (result.isNotEmpty()) Success(result)
        else Error(Exception("Unable to access network"))
    }

    private suspend fun storeData(cards: List<SimplifiedCard>) {
        cards.forEach {
            if(db.getByTitle(it.title) == null)
                db.insert(it)
        }
    }
}