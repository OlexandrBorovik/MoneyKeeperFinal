package com.example.moneykeeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moneykeeper.adapter.AccountAdapter;
import com.example.moneykeeper.adapter.CategoryAdapter;

import java.util.ArrayList;

public class AccountsSettingActivity extends AppCompatActivity {



      RecyclerView accountRecycler, categoryRecycler ;
      EditText newAccount, newCategory;
      AccountAdapter adapter;
    CategoryAdapter categoryAdapter;
      Button addAccount, addCategory, back;


   public static ArrayList<Account> accountList = new ArrayList<>();

   ArrayList<Category> categoryList = new ArrayList<>() ;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts_setting);

        addAccount = findViewById(R.id.button_add_account);
        addCategory=findViewById(R.id.button_add_category);
        newAccount=findViewById(R.id.add_account_name);
        newCategory=findViewById(R.id.add_category_name);
        back = findViewById(R.id.button_setting_back);
Bundle extras = getIntent().getExtras();
        if (extras != null) {
accountList=MainActivity.accountList;
categoryList=MainActivity.categoryList;
            setAccountResycler(accountList);
        }
        setCategoryResycler(categoryList);

        addAccount.setOnClickListener(view -> {
            String name = newAccount.getText().toString();
            if(!TextUtils.isEmpty(name)){
                Account account = new Account();
                account.setName(name);
                account.setBalance(0);
                accountList.add(account);
                newAccount.setText("");
                showInfo("Account added");


            }else{
                showInfo("Empty name");


            }


        });

        back.setOnClickListener(this::backToMain);
        addCategory.setOnClickListener(view -> {
            String name = newCategory.getText().toString();
            if(!TextUtils.isEmpty(name)){
                Category category = new Category();
                category.setCategoryName(name);

                categoryList.add(category);
                newCategory.setText("");
                showInfo("Category added");
            }else{
                showInfo("Empty name");

            }


        });
    }

    private void backToMain(View v) {

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    @SuppressLint("WrongViewCast")
    private void setAccountResycler(ArrayList<Account> accountList) {
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        accountRecycler = findViewById(R.id.accounts_list_setting);
        accountRecycler.setLayoutManager(layoutManager);

        adapter = new AccountAdapter(this, accountList);
        accountRecycler.setAdapter(adapter);
        adapter.setOnItemClickListener(position -> {
            accountList.remove(position);
            adapter.notifyItemRemoved(position);
        });

    }

    @SuppressLint("WrongViewCast")
    private void setCategoryResycler(ArrayList<Category> categoryList) {
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        categoryRecycler = findViewById(R.id.category_list_setting);
        categoryRecycler.setLayoutManager(layoutManager);
        categoryRecycler.addItemDecoration(new SpaceItemDecaration(1));
        categoryAdapter = new CategoryAdapter(this, categoryList);
        categoryRecycler.setAdapter(categoryAdapter);
        categoryAdapter.setOnItemClickListener(position -> {
            categoryList.remove(position);
            categoryAdapter.notifyItemRemoved(position);
        });

    }

    private void showInfo(String text) {
        Toast.makeText(this,text, Toast.LENGTH_SHORT).show();


    }




}