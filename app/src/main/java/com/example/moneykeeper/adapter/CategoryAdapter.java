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
import com.example.moneykeeper.Category;
import com.example.moneykeeper.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    Context context;
    List<Category> categoryList;
    private CategoryAdapter.OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(CategoryAdapter.OnItemClickListener clickListener){
        listener = clickListener;
    }
    public CategoryAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View categoryItems = LayoutInflater.from(context).inflate(R.layout.category_list_layout, parent, false);
        return new CategoryViewHolder(categoryItems,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {

        holder.categoryName.setText(categoryList.get(position).getCategoryName());


    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static final class CategoryViewHolder extends RecyclerView.ViewHolder{

        TextView categoryName;
        Button deleteButtonCategory;
        public CategoryViewHolder(@NonNull View itemView, CategoryAdapter.OnItemClickListener listener) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.category_list_name);
            deleteButtonCategory = itemView.findViewById(R.id.button_delete_category);
            deleteButtonCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}

