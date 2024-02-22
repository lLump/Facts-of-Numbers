package com.example.factsofdigits.presentation.ui.recycler.model

import android.content.Context
import android.widget.LinearLayout
import android.widget.TextView
import com.example.factsofdigits.R

class FactView(context: Context): LinearLayout(context) {
    var numberView: TextView? = null
    var infoView: TextView? = null

    init {
        inflate(context, R.layout.fact_view, this)

        numberView = findViewById(R.id.textFactNumber)
        infoView = findViewById(R.id.textFactInfo)
    }
}