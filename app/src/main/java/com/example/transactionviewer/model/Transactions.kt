package com.example.transactionviewer.model

data class Transactions(
 var id: String,
 var date: String,
 var amount: Double,
 var type: String,
 var description: String,
 var status: String,

)
