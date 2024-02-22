package com.example.factsofdigits.data.api

import com.example.factsofdigits.domain.FactRepository

class FactRepoImpl(private val api: NumbersApi) : FactRepository {
    override suspend fun requestFact(number: String, type: String): String {
        var fact = "ERROR"
        try {
            val response = api.getFact(number, type)
            if (response.isSuccessful) {
                fact = response.body() ?: "NULL response"
            } else {
                throw Exception("Error while requesting FACT: ${response.code()}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return fact
    }
}