package com.hkrt.designmode.sample3;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class WrapperRecyclerView extends RecyclerView{

    WrapperRecyclerAdapter mAdapter;

    public WrapperRecyclerView(@NonNull Context context) {
        super(context);
    }

    public WrapperRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapperRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setAdapter(@Nullable Adapter adapter) {
        mAdapter = new WrapperRecyclerAdapter(adapter);
        super.setAdapter(mAdapter);
    }

    public void addHeaderView(View view){
        if(mAdapter != null){
            mAdapter.addHeaderView(view);
        }
    }

    public void addFooterView(View view){
        if(mAdapter != null){
            mAdapter.addFooterView(view);
        }
    }

    public void removeHeaderView(View view){
        if(mAdapter != null){
            mAdapter.removeHeaderView(view);
        }
    }

    public void removeFooterView(View view){
        if(mAdapter != null){
            mAdapter.removeFooterView(view);
        }
    }
}
