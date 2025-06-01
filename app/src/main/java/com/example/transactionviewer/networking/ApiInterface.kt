package com.example.transactionviewer.networking

import androidx.lifecycle.LiveData
import com.example.transactionviewer.model.Transactions
import retrofit2.Retrofit
import retrofit2.http.GET

interface ApiInterface {

    @GET("aeed-0658-4d15-bff1")
    suspend fun getData(): List<Transactions>

}