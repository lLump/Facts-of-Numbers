package com.example.factsofdigits.presentation

import android.app.Application
import androidx.room.Room
import com.example.factsofdigits.data.room.dao.AppDatabase
import com.example.factsofdigits.di.AppContainer

class MyApplication: Application() {
    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer()
        initRoom()
    }

    private fun initRoom() {
        val database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "list_of_facts"
        ).build()
        appContainer.provideRoomDB(database)
    }
}