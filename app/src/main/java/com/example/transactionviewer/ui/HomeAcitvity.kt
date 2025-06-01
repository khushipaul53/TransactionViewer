package com.example.transactionviewer.ui

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.transactionviewer.Adapter.TransactionAdapter
import com.example.transactionviewer.R
import com.example.transactionviewer.databinding.ActivityHomeAcitvityBinding
import com.example.transactionviewer.model.Transactions
import com.example.transactionviewer.viewModel.TransactionViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog


class HomeAcitvity : AppCompatActivity() {
    lateinit var binding: ActivityHomeAcitvityBinding
    private lateinit var viewModel: TransactionViewModel
    private var allTransactions: List<Transactions> = ArrayList<Transactions>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeAcitvityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvTransactions.layoutManager = LinearLayoutManager(this)



        val transactionAdapter = TransactionAdapter(emptyList()) { transaction ->
            // Open bottom sheet with clicked transaction details
            showTransactionBottomSheet(transaction)
        }
        
        binding.rvTransactions.adapter = transactionAdapter
        //
        viewModel = ViewModelProvider(this)[TransactionViewModel::class.java]

//                  Log.e("qwe",viewModel.transactions.value?.size.toString())
        viewModel.transactions.observe(this) { transactions ->
            Log.d("HomeActvity", "API Response size: ${transactions.size}")
            allTransactions=transactions
            transactionAdapter.updateTransactions(transactions)


        }

        binding.btnAll.setOnClickListener {
            transactionAdapter.filterByType(null,allTransactions) // show all transactions
        }

        binding.btnDebit.setOnClickListener {
            transactionAdapter.filterByType("Debit",allTransactions)
        }

        binding.btnCredit.setOnClickListener {
            transactionAdapter.filterByType("Credit",allTransactions)
        }
        binding.btnDate.setOnClickListener {
            transactionAdapter.sortBy("date",allTransactions)
        }
        binding.btnAmount.setOnClickListener {
            transactionAdapter.sortBy("amount",allTransactions)
        }

    }
    private fun showTransactionBottomSheet(transaction: Transactions) {
        val bottomSheetDialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.bottom_sheet_transaction, null)

        // Bind data
        view.findViewById<TextView>(R.id.tvTransactionId).text = "Transaction ID: ${transaction.id}"
        view.findViewById<TextView>(R.id.tvTransactionDate).text = "Date: ${transaction.date}"
        view.findViewById<TextView>(R.id.tvTransactionAmount).text = "Amount: ${transaction.amount}"
        view.findViewById<TextView>(R.id.tvTransactionType).text = "Type: ${transaction.type}"
        view.findViewById<TextView>(R.id.tvTransactionStatus).text = "Status: ${transaction.status}"

        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }

}

