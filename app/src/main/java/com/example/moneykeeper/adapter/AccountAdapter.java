package com.example.moneykeeper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneykeeper.Account;
import com.example.moneykeeper.R;

import java.util.ArrayList;
import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountViewHolder> {

    private Context context;
  private   List<Account> accountList;
    private OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener clickListener){
        listener = clickListener;
    }

    public AccountAdapter(Context context, ArrayList<Account> accountList) {
        this.context = context;
        this.accountList = accountList;
    }

    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View accountItems = LayoutInflater.from(context).inflate(R.layout.list_layout, parent, false);


        return new AccountViewHolder(accountItems,listener);
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

        Button deleteButton;

        public AccountViewHolder(@NonNull View itemView,OnItemClickListener listener) {
            super(itemView);
            accountName = itemView.findViewById(R.id.account_list_name);
            accountBalance = itemView.findViewById(R.id.account_list_balance);
            deleteButton = itemView.findViewById(R.id.button_delete);

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(getAdapterPosition());
                }
            });

        }
    }
}

