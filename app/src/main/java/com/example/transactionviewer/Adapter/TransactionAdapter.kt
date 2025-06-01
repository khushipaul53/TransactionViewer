package com.example.transactionviewer.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.example.transactionviewer.R
import com.example.transactionviewer.model.Transactions

class TransactionAdapter (
    private var transactions: List<Transactions>,
    private val onItemClick: (Transactions) -> Unit
) : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {
    private val allTransactions: List<Transactions> = ArrayList(transactions)


//

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.transaction_items,
                parent,
                false
            )
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = transactions[position]
        holder.bind(transaction)
    }

    override fun getItemCount(): Int {
        return transactions.size    }

    fun updateTransactions(trns: List<Transactions>) {
        transactions=trns
        (allTransactions as ArrayList).clear()
        (allTransactions as ArrayList).addAll(trns)
        notifyDataSetChanged()
    }
    fun filterByType(type: String?,trns: List<Transactions>,) {
       if (type.isNullOrEmpty()) {
            transactions=trns
        } else {
            transactions=trns.filter { it.type.equals(type, ignoreCase = true) }
        }
        notifyDataSetChanged()
    }
    fun sortBy(type: String?,trns: List<Transactions>,) {
        if (type.isNullOrEmpty()) {
            transactions=trns
        } else if(type.equals("date")) {
            transactions = trns.sortedByDescending { it.date }

            Log.d("sdfg",""+  transactions[0].amount)
        }
            else if(type.equals("amount")) {
                transactions = trns.sortedByDescending { it.amount }
// descending = most recent first

        }
        notifyDataSetChanged()
    }

    inner class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvDate: TextView = itemView.findViewById(R.id.tvDate)
        private val tvType: TextView = itemView.findViewById(R.id.tvType)
        private val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        private val tvAmount: TextView = itemView.findViewById(R.id.tvAmount)



        fun bind(transaction: Transactions) {
            tvDate.text = "Date: ${transaction.date}"
            tvType.text = "Type: ${transaction.type}"
            tvDescription.text = "Description: ${transaction.description}"
            tvAmount.text = "Amount: $ ${transaction.amount}"


            itemView.setOnClickListener {


                onItemClick(transaction)

            }
        }


    }
}


