package com.example.factsofdigits.data.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FactDto(
    @PrimaryKey val number: Int,
    val info: String
)
