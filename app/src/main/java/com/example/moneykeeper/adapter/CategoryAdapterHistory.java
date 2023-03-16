package com.example.moneykeeper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneykeeper.Category;
import com.example.moneykeeper.R;

import java.util.List;

public class CategoryAdapterHistory extends RecyclerView.Adapter<CategoryAdapterHistory.CategoryViewHolder> {

    Context context;
    List<Category> categoryList;
    private CategoryAdapterHistory.OnItemClickListener listener;

    public CategoryAdapterHistory() {

    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(CategoryAdapterHistory.OnItemClickListener clickListener){
        listener = clickListener;
    }
    public CategoryAdapterHistory(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View categoryItems = LayoutInflater.from(context).inflate(R.layout.history_layout, parent, false);
        return new CategoryViewHolder(categoryItems,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {

        holder.categoryName.setText(categoryList.get(position).getCategoryName());
        String balance = Double.toString(categoryList.get(position).getCategoryBalance());
        holder.categoryBalance.setText(balance);
        holder.date.setText(categoryList.get(position).getDate());

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static final class CategoryViewHolder extends RecyclerView.ViewHolder{

        TextView categoryName;
        TextView categoryBalance;
        TextView date;
        public CategoryViewHolder(@NonNull View itemView, CategoryAdapterHistory.OnItemClickListener listener) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.history_name);
           categoryBalance=itemView.findViewById(R.id.history_balance);
           date=itemView.findViewById(R.id.history_date);
        }
    }
}

