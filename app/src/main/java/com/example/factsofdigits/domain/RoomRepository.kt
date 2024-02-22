package com.example.factsofdigits.domain

interface RoomRepository {
    //    fun saveAllFacts(facts: List<Pair<Int, String>>)
    fun saveFact(number: Int, info: String)

    suspend fun getFactsHistory(): List<Pair<String, String>>

    fun deleteAll()

}