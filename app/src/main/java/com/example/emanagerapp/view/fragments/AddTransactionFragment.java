package com.example.emanagerapp.view.fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.emanagerapp.R;

import com.example.emanagerapp.adapter.AccountAdapter;
import com.example.emanagerapp.adapter.CategoryAdapter;
import com.example.emanagerapp.databinding.FragmentAddTransactionBinding;
import com.example.emanagerapp.databinding.ListDialogBinding;
import com.example.emanagerapp.models.Account;
import com.example.emanagerapp.models.Category;
import com.example.emanagerapp.utils.Constants;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class AddTransactionFragment extends BottomSheetDialogFragment {







    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    FragmentAddTransactionBinding binding;


    @Override
    public  View onCreateView(LayoutInflater inflater, ViewGroup container,
                                                    Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentAddTransactionBinding.inflate(inflater);

        binding.incomeBtn.setOnClickListener(view -> {
            binding.incomeBtn.setBackground(getContext().getDrawable(R.drawable.income_selector));
            binding.expenseBtn.setBackground(getContext().getDrawable(R.drawable.default_selector));
            binding.expenseBtn.setTextColor(getContext().getColor(R.color.textColour));
            binding.incomeBtn.setTextColor(getContext().getColor(R.color.green));
        });

        binding.expenseBtn.setOnClickListener(view -> {
            binding.incomeBtn.setBackground(getContext().getDrawable(R.drawable.default_selector));
            binding.expenseBtn.setBackground(getContext().getDrawable(R.drawable.expense_selector));
            binding.incomeBtn.setTextColor(getContext().getColor(R.color.textColour));
            binding.expenseBtn.setTextColor(getContext().getColor(R.color.red));
        });

        binding.date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(getContext());
                datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        Calendar calendar=Calendar.getInstance();
                        calendar.set(Calendar.DAY_OF_MONTH,datePicker.getDayOfMonth());
                        calendar.set(Calendar.MONTH,datePicker.getMonth());
                        calendar.set(Calendar.YEAR,datePicker.getYear());

                        SimpleDateFormat dateFormat=new SimpleDateFormat("dd MMMM,yyyy");
                        String dateToshow=dateFormat.format(calendar.getTime());
                        binding.date.setText(dateToshow);

                    }
                });
                datePickerDialog.show();
            }
        });

        binding.category.setOnClickListener(c -> {
            ListDialogBinding dialogBinding;
            dialogBinding = ListDialogBinding.inflate(inflater);
            AlertDialog categoryDialog=new AlertDialog.Builder(getContext()).create();
            categoryDialog.setView(dialogBinding.getRoot());



            CategoryAdapter categoryAdapter=new CategoryAdapter(getContext(), Constants.categories, new CategoryAdapter.categoryClickListener() {
                @Override
                public void onCategoryClicked(Category category) {
                    binding.category.setText(category.getCategoryName());
                    categoryDialog.dismiss();
                }
            });
            dialogBinding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
            dialogBinding.recyclerView.setAdapter(categoryAdapter);

            categoryDialog.show();
        });

        binding.account.setOnClickListener(c->{
            ListDialogBinding dialogBinding;
            dialogBinding = ListDialogBinding.inflate(inflater);
            AlertDialog accountsDialog=new AlertDialog.Builder(getContext()).create();
            accountsDialog.setView(dialogBinding.getRoot());

            ArrayList<Account> accounts=new ArrayList<>();
            accounts.add(new Account(0,"cash"));
            accounts.add(new Account(0,"Bank"));
            accounts.add(new Account(0,"Paytm"));
            accounts.add(new Account(0,"EasyPaisa"));
            accounts.add(new Account(0,"Other"));

            AccountAdapter adapter=new AccountAdapter(getContext(), accounts, new AccountAdapter.AccountsClickListener() {
                @Override
                public void onAccountSelected(Account account) {
                    binding.account.setText(account.getAccountName());
                    accountsDialog.dismiss();
                }
            });
            dialogBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            dialogBinding.recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL ));
            dialogBinding.recyclerView.setAdapter(adapter);
            accountsDialog.show();
        });
        return binding.getRoot();
    }
}