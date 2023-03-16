package com.example.moneykeeper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneykeeper.Account;
import com.example.moneykeeper.R;

import java.util.ArrayList;
import java.util.List;

public class AccountAdapterMain extends RecyclerView.Adapter<AccountAdapterMain.AccountViewHolder> {

    Context context;
    List<Account> accountList;

    public AccountAdapterMain(Context context, ArrayList<Account> accountList) {
        this.context = context;
        this.accountList = accountList;
    }

    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View accountItems = LayoutInflater.from(context).inflate(R.layout.account_list_layout, parent, false);
        return new AccountViewHolder(accountItems);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder holder, int position) {

        holder.accountName.setText(accountList.get(position).getName());

        String balanceText = String.valueOf(accountList.get(position).getBalance());
        holder.accountBalance.setText(balanceText);

    }

    @Override
    public int getItemCount() {
        return accountList.size();
    }

    public static final class AccountViewHolder extends RecyclerView.ViewHolder{

        TextView accountName;
        TextView accountBalance;

        public AccountViewHolder(@NonNull View itemView) {
            super(itemView);
            accountName = itemView.findViewById(R.id.account_list_name_main);
            accountBalance = itemView.findViewById(R.id.account_list_balance_main);

        }
    }
}

