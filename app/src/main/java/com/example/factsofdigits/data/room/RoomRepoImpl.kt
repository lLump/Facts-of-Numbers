package com.example.factsofdigits.data.room

import com.example.factsofdigits.data.room.dao.FactsDao
import com.example.factsofdigits.data.room.model.FactDto
import com.example.factsofdigits.domain.RoomRepository

class RoomRepoImpl(private val dao: FactsDao) : RoomRepository {

    override suspend fun getFactsHistory(): List<Pair<String, String>> {
        return dao.getSavedFacts().map { Pair(it.number.toString(), it.info) }
    }

    override fun saveFact(number: Int, info: String) {
        dao.saveFact(FactDto(number, info))
    }

    override fun deleteAll() {
        dao.clear()
    }

//    override fun saveAllFacts(facts: List<Pair<Int, String>>) {
//        dao.saveAllFacts(facts.map { FactDto(it.first, it.second) })
//    }
}
