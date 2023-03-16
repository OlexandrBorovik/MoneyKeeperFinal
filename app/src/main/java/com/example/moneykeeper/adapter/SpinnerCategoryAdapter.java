package com.example.moneykeeper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.moneykeeper.Account;
import com.example.moneykeeper.Category;
import com.example.moneykeeper.R;

import java.util.ArrayList;

public class SpinnerCategoryAdapter extends BaseAdapter {

    Context context;
    ArrayList<Category> categoryArrayList;

    public SpinnerCategoryAdapter(Context context, ArrayList<Category> categoryArrayList) {
        this.context = context;
        this.categoryArrayList = categoryArrayList;
    }

    @Override
    public int getCount() {
        return categoryArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryArrayList.get(position);
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
            textView.setText(categoryArrayList.get(position).getCategoryName());
        }
        return convertView;
    }
}
