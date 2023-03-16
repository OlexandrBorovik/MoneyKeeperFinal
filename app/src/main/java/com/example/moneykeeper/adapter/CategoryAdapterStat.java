package com.example.moneykeeper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneykeeper.Category;
import com.example.moneykeeper.R;

import java.util.List;

public class CategoryAdapterStat extends RecyclerView.Adapter<CategoryAdapterStat.CategoryViewHolder> {

    Context context;
    List<Category> categoryList;
    private CategoryAdapterStat.OnItemClickListener listener;

    public CategoryAdapterStat() {

    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(CategoryAdapterStat.OnItemClickListener clickListener){
        listener = clickListener;
    }
    public CategoryAdapterStat(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View categoryItems = LayoutInflater.from(context).inflate(R.layout.profit_expence_layout, parent, false);
        return new CategoryViewHolder(categoryItems,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {

        holder.categoryName.setText(categoryList.get(position).getCategoryName());
        String balance = Double.toString(categoryList.get(position).getCategoryBalance());
holder.categoryBalance.setText(balance);

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static final class CategoryViewHolder extends RecyclerView.ViewHolder{

        TextView categoryName;
        TextView categoryBalance;
        public CategoryViewHolder(@NonNull View itemView, CategoryAdapterStat.OnItemClickListener listener) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.category_name_stat);
           categoryBalance=itemView.findViewById(R.id.category_balance_stat);
        }
    }
}

