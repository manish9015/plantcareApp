package com.example.emanagerapp.adapter;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.inputmethodservice.Keyboard;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.emanagerapp.R;
import com.example.emanagerapp.databinding.RowTransactionBinding;
import com.example.emanagerapp.models.Category;
import com.example.emanagerapp.models.Transaction;
import com.example.emanagerapp.utils.Constants;
import com.example.emanagerapp.utils.Helper;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {

    Context context;
    ArrayList<Transaction> transactions;

    public TransactionAdapter(Context context, ArrayList<Transaction> transactions){
        this.context=context;
        this.transactions=transactions;
    }
    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TransactionViewHolder(LayoutInflater.from(context).inflate(R.layout.row_transaction,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        Transaction transaction=transactions.get(position);
        holder.binding.transactionAmount.setText(String.valueOf(transaction.getAmount()));
        holder.binding.accountLbl.setText(transaction.getAccount());
        holder.binding.TransactionDate.setText(Helper.formatDate(transaction.getDate() ));
        holder.binding.transactionCategory.setText(transaction.getCategory());

        Category transactionCategory=Constants.getCategoryDetails(transaction.getCategory());
        holder.binding.categoryItem.setImageResource(transactionCategory.getCategoryImage());
        holder.binding.categoryItem.setBackgroundTintList(context.getColorStateList(transactionCategory.getCategoryColor()));

        holder.binding.accountLbl.setBackgroundTintList(context.getColorStateList(Constants.getAccountsColor(transaction.getAccount())));
        if (transaction.getType().equals(Constants.INCOME))
        {
            holder.binding.transactionAmount.setTextColor(context.getColor(R.color.green));
        }
        else  if (transaction.getType().equals(Constants.EXPENSE))
        {
            holder.binding.transactionAmount.setTextColor(context.getColor(R.color.red));
        }



    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public class TransactionViewHolder extends RecyclerView.ViewHolder {

        RowTransactionBinding binding;
        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            binding= RowTransactionBinding.bind(itemView);
        }
    }
}
