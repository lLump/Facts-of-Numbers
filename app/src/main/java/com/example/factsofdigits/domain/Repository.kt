package com.example.factsofdigits.domain

class Repository(private val remote: FactRepository, private val local: RoomRepository) {

    suspend fun getFactInfo(number: String, type: String) = remote.requestFact(number, type)

    suspend fun getSavedFacts() = local.getFactsHistory()

    fun saveFact(number: String, info: String) {
        local.saveFact(number.toInt(), info)
    }

    fun deleteAllFacts() {
        local.deleteAll()
    }

}