package com.example.factsofdigits.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.factsofdigits.data.room.model.FactDto

@Dao
interface FactsDao {

    @Query("SELECT * FROM factdto")
    suspend fun getSavedFacts(): List<FactDto>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveFact(fact: FactDto)

    @Query("DELETE FROM factdto")
    fun clear()
}