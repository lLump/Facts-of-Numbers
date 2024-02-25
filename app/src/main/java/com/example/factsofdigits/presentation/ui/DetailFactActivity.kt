package com.example.factsofdigits.presentation.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.factsofdigits.databinding.ActivityDetailFactBinding

class DetailFactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailFactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val number = intent.getStringExtra("number")
        val info = intent.getStringExtra("info")

        binding.textDetailNumber.text = number
        binding.textDetailInfo.text = info
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    companion object {
        fun newInstance(context: Context, number: String, info: String): Intent {
            val intent = Intent(context, DetailFactActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            val args = Bundle().apply {
                putString("number", number)
                putString("info", info)
            }
            intent.putExtras(args)
            return intent
        }
    }
}