package dev.eighteentech.acc.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.eighteentech.acc.entity.SimplifiedCard

@Dao
interface CardDao {

    @Query("SELECT * FROM SimplifiedCard")
    suspend fun getAll(): List<SimplifiedCard>

    @Query("SELECT * FROM SimplifiedCard WHERE title = :title")
    suspend fun getByTitle(title: String): SimplifiedCard?

    @Insert
    suspend fun insert(card:SimplifiedCard)
}