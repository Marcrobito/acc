package dev.eighteentech.acc.room

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.eighteentech.acc.entity.SimplifiedCard

@Database(
    entities = [SimplifiedCard::class],
    version = 1
)
abstract class DB : RoomDatabase() {
    abstract fun cardDao() : CardDao
}