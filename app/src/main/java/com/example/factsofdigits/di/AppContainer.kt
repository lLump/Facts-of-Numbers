package com.example.factsofdigits.di

import com.example.factsofdigits.data.api.FactRepoImpl
import com.example.factsofdigits.data.api.NumbersApi
import com.example.factsofdigits.data.room.RoomRepoImpl
import com.example.factsofdigits.data.room.dao.AppDatabase
import com.example.factsofdigits.domain.Repository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class AppContainer {
    lateinit var repository: Repository

    fun provideRoomDB(database: AppDatabase) {
        initRepository(database)
    }

    private fun initRepository(roomDB: AppDatabase) {
        repository = Repository(
            remote = FactRepoImpl(getRetrofit()),
            local = RoomRepoImpl(roomDB.factsDao())
        )
    }

    private fun getRetrofit() = Retrofit.Builder()
        .baseUrl("http://numbersapi.com/")
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()
        .create(NumbersApi::class.java)
}