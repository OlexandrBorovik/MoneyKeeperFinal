package com.example.moneykeeper;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.moneykeeper.adapter.AccountAdapterMain;
import com.example.moneykeeper.adapter.CategoryAdapterStat;
import com.example.moneykeeper.adapter.CategoryAdapterStatEx;
import com.example.moneykeeper.adapter.SpinnerAccountAdapter;
import com.example.moneykeeper.adapter.SpinnerCategoryAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    static public ArrayList<Account> accountList = new ArrayList<>();
    static public ArrayList<Category> categoryList = new ArrayList<>();

    static public ArrayList<Category> historyTransaction= new ArrayList<>();
    public ArrayList<Category> profit = new ArrayList<>();

    public ArrayList<Category> expense = new ArrayList<>();

    Account one = new Account("bank", 0);


    Date currentDate = new Date();
    DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
    String date = dateFormat.format(currentDate);
    Category two = new Category("salary", 0);

    RecyclerView accountRecycler, profitResycler, expenceResycler;
    AccountAdapterMain adapter;
    CategoryAdapterStat statAdapter ;
CategoryAdapterStatEx expenceAdapter;
    SpinnerAccountAdapter spinnerAccountAdapter;
    SpinnerCategoryAdapter spinnerCategoryAdapter;
    TextView summa;

    EditText enterSumma;
    Spinner accountSpinner, categorySpinner;
    ImageButton addMoney, deductMoney;
    Button  historyButton;
    Account selectedAccount;
    Category selectedCategory;


    ActivityResultLauncher<Intent> mSatartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {

                if (result.getResultCode() == Activity.RESULT_OK) {

                    accountList= AccountsSettingActivity.accountList;
                    setAccountResycler(accountList);
                }
            });


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        accountList.add(one);

        setAccountResycler(accountList);

        categoryList.add(two);

        summa = findViewById(R.id.summa);

        setCategoryResyclerStat(profit);
        setCategoryResyclerStatEx(expense);

        addMoney = findViewById(R.id.buttonPlus);
        deductMoney = findViewById(R.id.buttonMinus);

        spinnerAccountAdapter = new SpinnerAccountAdapter(this, accountList);
        spinnerCategoryAdapter = new SpinnerCategoryAdapter(this, categoryList);
        /////////////////////
        categorySpinner = findViewById(R.id.categorySpinner);
        categorySpinner.setAdapter(spinnerCategoryAdapter);
        /////////////////////////
        enterSumma = findViewById(R.id.enterNumber);
        historyButton = findViewById(R.id.button_history);

        accountSpinner = findViewById(R.id.accountSpinner);
        accountSpinner.setAdapter(spinnerAccountAdapter);


        selectedCategory = (Category) categorySpinner.getSelectedItem();


        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedAccount = (Account) parent.getSelectedItem();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        accountSpinner.setOnItemSelectedListener(itemSelectedListener);

        AdapterView.OnItemSelectedListener itemSelectedListenerCategory = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCategory = (Category) parent.getSelectedItem();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        categorySpinner.setOnItemSelectedListener(itemSelectedListenerCategory);
        //////

        historyButton.setOnClickListener(this::buttonHistory);

        deductMoney.setOnClickListener(v -> changeBalance(0));

        addMoney.setOnClickListener(view -> changeBalance(1));


    }


    private void suma() {

        double money = 0;
        for (int i = 0; i < accountList.size(); i++) {
            money = money + accountList.get(i).getBalance();
            summa.setText(String.valueOf(money));
        }
    }


    @SuppressLint("WrongViewCast")
    private void setAccountResycler(ArrayList<Account> accountList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        accountRecycler = findViewById(R.id.accounts_list_main);
        accountRecycler.setLayoutManager(layoutManager);
        // accountRecycler.addItemDecoration(new SpaceItemDecaration(1));
        adapter = new AccountAdapterMain(this, accountList);
        accountRecycler.setAdapter(adapter);

    }
    @SuppressLint("WrongViewCast")
    private void setCategoryResyclerStat(ArrayList<Category> profit) {

        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        profitResycler = findViewById(R.id.profitResycler);
        profitResycler.setLayoutManager(layoutManager);
        statAdapter= new CategoryAdapterStat(this, profit);
        profitResycler.setAdapter(statAdapter);


    }

    @SuppressLint("WrongViewCast")
    private void setCategoryResyclerStatEx(ArrayList<Category> expense) {
        RecyclerView.LayoutManager layoutManagerEx= new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        expenceResycler = findViewById(R.id.exspenceResycler);

        expenceResycler.setLayoutManager(layoutManagerEx);
        expenceAdapter= new CategoryAdapterStatEx(this, expense);

        expenceResycler.setAdapter(expenceAdapter);


    }
    public void buttonSetting(View view) {
        Intent intent = new Intent(this, AccountsSettingActivity.class);
        intent.putExtra("listA", accountList);

        mSatartForResult.launch(intent);

    }
    public void buttonHistory(View view) {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);

    }



    @SuppressLint("NotifyDataSetChanged")
    private void changeBalance(int j) {
        if (enterSumma.getText().length() > 0) {
            String text = enterSumma.getText().toString();
            double enterSum = Double.parseDouble(text);
            String accountName = selectedAccount.getName();
            for(Account account:accountList) {
                if (account.getName().equals(accountName)) {
                    double index = account.getBalance();
                    if (j > 0) {
                        account.setBalance(index + enterSum);
                        addProfit(enterSum);

                    } if(j==0) {
                        account.setBalance(index - enterSum);
                        addExpense(enterSum);
                    }

                }

                setAccountResycler(accountList);
            }
            statAdapter.notifyDataSetChanged();
            expenceAdapter.notifyDataSetChanged();

            Category category = new Category();

            category.setCategoryName(selectedCategory.getCategoryName());
            category.setCategoryBalance(enterSum);
            category.setDate(date);
            historyTransaction.add(category);

        }

        enterSumma.setText("");
        suma();

    }

    private void addProfit(double money) {
            for (Category catego:profit) {
                if (catego.getCategoryName().equals(selectedCategory.getCategoryName())) {
                    double summa = money + catego.getCategoryBalance();
                    catego.setCategoryBalance(summa);

                }
            }
            selectedCategory.setCategoryBalance(money);
            profit.add(selectedCategory);

    }

    private void addExpense(double money) {
        Category category = new Category();
        for (Category catego:expense) {
            if (catego.getCategoryName().equals(selectedCategory.getCategoryName())) {
                double summa = money + catego.getCategoryBalance();
                catego.setCategoryBalance(summa);

            }
        }
        category.setCategoryBalance(money);
        category.setCategoryName(selectedCategory.getCategoryName());

        expense.add(category);

    }


}




