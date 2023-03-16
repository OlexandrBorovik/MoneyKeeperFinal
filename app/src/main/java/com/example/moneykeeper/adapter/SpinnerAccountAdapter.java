package com.example.moneykeeper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.moneykeeper.Account;
import com.example.moneykeeper.R;

import java.util.ArrayList;

public class SpinnerAccountAdapter  extends BaseAdapter {

    Context context;
    ArrayList<Account> accountArrayList;

    public SpinnerAccountAdapter(Context context, ArrayList<Account> accountArrayList) {
        this.context = context;
        this.accountArrayList = accountArrayList;
    }

    @Override
    public int getCount() {
        return accountArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return accountArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.layout_spinner, null,false);
            TextView textView = convertView.findViewById(R.id.spinnerText);
            textView.setText(accountArrayList.get(position).getName());
        }
        return convertView;
    }
}
