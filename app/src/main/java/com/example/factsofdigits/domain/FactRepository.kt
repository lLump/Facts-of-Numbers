package com.example.factsofdigits.domain

interface FactRepository {
    suspend fun requestFact(number: String, type: String): String
}