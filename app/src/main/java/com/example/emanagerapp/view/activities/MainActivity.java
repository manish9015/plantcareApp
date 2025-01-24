package com.example.emanagerapp.view.activities;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.emanagerapp.adapter.TransactionAdapter;
import com.example.emanagerapp.models.Transaction;
import com.example.emanagerapp.utils.Constants;
import com.example.emanagerapp.utils.Helper;
import com.example.emanagerapp.view.fragments.AddTransactionFragment;
import com.example.emanagerapp.R;
import com.example.emanagerapp.databinding.ActivityMainBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");
//
//        myRef.setValue("Hello firebase");


        setSupportActionBar(binding.toolBar);
        getSupportActionBar().setTitle("Transactions");

        Constants.setCategories();

        calendar=Calendar.getInstance();
        updateDate();

        binding.nextImagebtn.setOnClickListener(c->{
            calendar.add(calendar.DATE,1);
            updateDate();
        });

        binding.previousImagebtn.setOnClickListener(c->{
            calendar.add(calendar.DATE,-1);
            updateDate();
        });
        

        binding.floatingActionButton.setOnClickListener(c -> {
            new AddTransactionFragment().show(getSupportFragmentManager(),null);
        });
        ArrayList<Transaction> transactions=new ArrayList<>();
        transactions.add(new Transaction(Constants.INCOME,"Business","Cash","Some note here",new Date(),2,500));
        transactions.add(new Transaction(Constants.EXPENSE,"Investment","Cash","Some note here",new Date(),4,-400));
        transactions.add(new Transaction(Constants.INCOME,"Rent","Cash","Some note here",new Date(),6,509));
        transactions.add(new Transaction(Constants.INCOME,"Business","Cash","Some note here",new Date(),8,700));
        TransactionAdapter transactionAdapter=new TransactionAdapter(this,transactions);
        binding.transactionsList.setLayoutManager(new LinearLayoutManager(this));
        binding.transactionsList.setAdapter(transactionAdapter);
    }
    void  updateDate(){
            //SimpleDateFormat dateFormat=new SimpleDateFormat("dd MMMM yyyy");
        binding.currrentDate.setText(Helper.formatDate(calendar.getTime()));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}