package com.example.factsofdigits.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NumbersApi {

    @GET("{number}/{type}")
    suspend fun getFact(@Path("number") number: String, @Path("type") type: String): Response<String>

}