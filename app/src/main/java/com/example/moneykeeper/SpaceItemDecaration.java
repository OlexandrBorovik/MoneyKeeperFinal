package com.example.moneykeeper;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class SpaceItemDecaration extends RecyclerView.ItemDecoration {
    private int space;
    public SpaceItemDecaration(int space) {
        this.space=space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,RecyclerView parent, RecyclerView.State state){
        super.getItemOffsets(outRect,view,parent,state);
        if(parent.getAdapter()!=null && parent.getChildLayoutPosition(view)==parent.getAdapter().getItemCount()-1){
            outRect.bottom=space;
        }
    }
}
