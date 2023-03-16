package com.example.moneykeeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.moneykeeper.adapter.CategoryAdapterHistory;


import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
ArrayList<Category>categories = new ArrayList<>();
Button back;
RecyclerView transaction;
CategoryAdapterHistory categoryAdapterHistory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
categories = MainActivity.historyTransaction;
        back = findViewById(R.id.button_history_back);
transaction = findViewById(R.id.history_list);
setCategoryResycler(categories);




        back.setOnClickListener(v -> backToMain ());




    }

    private void backToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    @SuppressLint("WrongViewCast")
    private void setCategoryResycler(ArrayList<Category> list) {
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        transaction.setLayoutManager(layoutManager);
        categoryAdapterHistory= new CategoryAdapterHistory(this, list);
        transaction.setAdapter(categoryAdapterHistory);


    }


}