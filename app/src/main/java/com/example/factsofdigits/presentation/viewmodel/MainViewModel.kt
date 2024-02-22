package com.example.factsofdigits.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.factsofdigits.domain.Repository
import com.example.factsofdigits.presentation.MyApplication
import com.example.factsofdigits.presentation.viewmodel.model.FactInfo
import com.example.factsofdigits.presentation.viewmodel.model.FactType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random
import kotlin.random.nextInt

class MainViewModel(private val repo: Repository) : ViewModel() {
    private val _numberInfo = MutableSharedFlow<FactInfo>()
    val numberInfo: SharedFlow<FactInfo> = _numberInfo

    fun getFact(number: String, typeId: Int) {
        requestAndSaveInfo(
            number = number,
            type = FactType.getType(typeId)
        )
    }

    fun getRandomFact() {
//        val random = Random(System.currentTimeMillis())
        val rNumber = Random.nextInt(7777).toString()
        val rType = FactType.getType(Random.nextInt(1..4))

        requestAndSaveInfo(rNumber, rType)
    }

    fun saveFact(fact: FactInfo) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.saveFact(
                number = fact.number,
                info = fact.info
            )
        }
    }

    fun deleteFactsHistory() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteAllFacts()
        }
    }

    suspend fun getFactsHistory(): List<FactInfo> {
        return withContext(Dispatchers.IO) {
            repo.getSavedFacts().map { FactInfo(it.first, it.second) }
        }
    }

    private fun requestAndSaveInfo(number: String, type: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _numberInfo.emit(
                FactInfo(
                    number = number,
                    info = repo.getFactInfo(number, type)
                )
            )
        }
    }

    companion object {
        @Suppress("UNCHECKED_CAST")
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val app = checkNotNull(extras[APPLICATION_KEY]) as MyApplication
                return MainViewModel(app.appContainer.repository) as T
            }
        }
    }
}