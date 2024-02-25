package com.example.factsofdigits.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.factsofdigits.databinding.ActivityMainBinding
import com.example.factsofdigits.presentation.ui.recycler.FactListAdapter
import com.example.factsofdigits.presentation.viewmodel.MainViewModel
import com.example.factsofdigits.presentation.viewmodel.model.FactInfo
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var rAdapter: FactListAdapter

    private val viewModel: MainViewModel by viewModels { MainViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListener()
        initRecycler()

        loadFactHistory()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.numberInfo.collect { fact ->
                    //Updating ↓
                    rAdapter.addFact(fact)   // Add factItem to Recycler
                    viewModel.saveFact(fact) // Save fact to local database
                }
            }
        }
    }

    private fun loadFactHistory() {
        viewModel.showFactsHistory(rAdapter) // Probably not the best idea
    }

    private fun startActivityByChosenFact(fact: FactInfo) {
        val intent = DetailFactActivity.newInstance(this, fact.number, fact.info)
        baseContext.startActivity(intent)
    }

    private fun initRecycler() {
        binding.recyclerView.let {
            it.layoutManager = LinearLayoutManager(this@MainActivity)
            it.adapter = FactListAdapter { fact ->
                startActivityByChosenFact(fact)
            }
            rAdapter = it.adapter as FactListAdapter
        }
    }

    private fun setListener() {
        val listener = createListener()
        binding.btnGetFact.setOnClickListener(listener)
        binding.btnGetRandomFact.setOnClickListener(listener)
        binding.btnClear.setOnClickListener(listener)
    }

    private fun createListener() = View.OnClickListener { view ->
        when (view.id) {
            binding.btnGetFact.id -> getChosenFact()
            binding.btnGetRandomFact.id -> viewModel.getRandomFact()
            binding.btnClear.id -> clearFacts()
        }
    }

    private fun getChosenFact() {
        viewModel.getFact(
            number = getTextNumberIfExist(),
            typeId = getSelectedRadioType()
        )
    }

    private fun getTextNumberIfExist(): String {
        val tNumber = binding.textEnterNumber.text.toString()
        return if (tNumber.isBlank()) {
            Toast.makeText(this, "Number is empty.\nZero was requested", Toast.LENGTH_SHORT).show()
            "0"
        } else tNumber
    }

    private fun clearFacts() {
        rAdapter.clearList()
        viewModel.deleteFactsHistory()
    }

    private fun getSelectedRadioType(): Int {
        return when (
            binding.radioGroup.checkedRadioButtonId //Selected radioButton
        ) {
            binding.radioYear.id -> 2
            binding.radioDate.id -> 3
            binding.radioMath.id -> 4
            else -> 1 //binding.radioTrivia.id (Default in numbersApi)
        }
    }
}