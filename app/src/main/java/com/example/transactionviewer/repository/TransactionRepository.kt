package com.example.transactionviewer.repository

import androidx.lifecycle.LiveData
import com.example.transactionviewer.model.Transactions
import com.example.transactionviewer.networking.ApiInterface
import com.example.transactionviewer.networking.RetrofitClient

class TransactionRepository {
    private val apiService = RetrofitClient.retrofit.create(ApiInterface::class.java)
    suspend fun getTransactions(): List<Transactions> {
        return apiService.getData() as List<Transactions>
    }
}