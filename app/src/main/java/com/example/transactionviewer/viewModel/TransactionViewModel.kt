package com.example.transactionviewer.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.transactionviewer.model.Transactions
import com.example.transactionviewer.networking.ApiInterface
import com.example.transactionviewer.networking.RetrofitClient
import com.example.transactionviewer.repository.TransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TransactionViewModel: ViewModel() {
    private val _transactions = MutableLiveData<List<Transactions>>()
    val transactions: LiveData<List<Transactions>> get() = _transactions
    private val apiService: ApiInterface by lazy { RetrofitClient.retrofit.create(ApiInterface::class.java)
    }

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiService.getData()
//                _transactions.value= response as List<Transactions>?
                Log.d("TransactionViewModel", "API Response size: ${response}")

                withContext(Dispatchers.Main) {
                    _transactions.value = response
                    Log.d("TransactionViewModel", "API Response size: ${response.size}")
                }

            } catch (e: Exception) {
                Log.d("errror",e.toString())
            } finally {
                Log.d("errror","dfrgrggthghy")
            }
        }

    }
}
