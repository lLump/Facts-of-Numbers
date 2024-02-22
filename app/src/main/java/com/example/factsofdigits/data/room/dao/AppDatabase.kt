package com.example.factsofdigits.data.room.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.factsofdigits.data.room.model.FactDto

@Database(entities = [FactDto::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun factsDao(): FactsDao
}