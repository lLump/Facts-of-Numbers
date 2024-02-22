package com.example.factsofdigits.presentation.viewmodel.model

enum class FactType(val type: String) {
    TRIVIA("trivia"),
    YEAR("year"),
    DATE("date"),
    MATH("math");

    operator fun invoke(): String = type

    companion object {
        fun getType(id: Int): String {
            return when (id) {
                1 -> TRIVIA()
                2 -> YEAR()
                3 -> DATE()
                4 -> MATH()
                else -> throw IllegalArgumentException("Unknown ID") // <- 4
            }
        }
    }
}
