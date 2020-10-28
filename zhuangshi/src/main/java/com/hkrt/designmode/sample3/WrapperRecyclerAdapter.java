package com.hkrt.designmode.sample3;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WrapperRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final RecyclerView.Adapter mRealAdapter;
    ArrayList<View> mHeaderViews;
    ArrayList<View> mFooterViews;


    public WrapperRecyclerAdapter(RecyclerView.Adapter realAdapter) {
        this.mRealAdapter = realAdapter;

        mRealAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                notifyDataSetChanged();
            }
        });

        mHeaderViews = new ArrayList<>();
        mFooterViews = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {

        int numHeaders = getHeaderCount();
        if(position < numHeaders){
            return createHeaderFooterViewHolder(mHeaderViews.get(position));
        }
        final int adjPosition = position - numHeaders;
        int adapterCount = 0;
        if(mRealAdapter != null){
            adapterCount = mRealAdapter.getItemCount();
            if(adjPosition < adapterCount){
                return mRealAdapter.onCreateViewHolder(parent,mRealAdapter.getItemViewType(adjPosition));
            }
        }
        return createHeaderFooterViewHolder(mFooterViews.get(adjPosition - adapterCount));
    }

    private RecyclerView.ViewHolder createHeaderFooterViewHolder(View view) {
        return new RecyclerView.ViewHolder(view){};
    }

    public int getHeaderCount() {
        return mHeaderViews.size();
    }

    public int getFooterCount(){
        return mFooterViews.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int numHeaders = getHeaderCount();
        if(position < numHeaders){
            return;
        }
        final int adjPosition = position - numHeaders;
        int adapterCount = 0;
        if(mRealAdapter != null){
            adapterCount = mRealAdapter.getItemCount();
            if(adjPosition < adapterCount){
                mRealAdapter.onBindViewHolder(holder,position);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mFooterViews.size() +  mRealAdapter.getItemCount()  +mHeaderViews.size();
    }

    public void addHeaderView(View view){
        if(!mHeaderViews.contains(view)){
            mHeaderViews.add(view);
            notifyDataSetChanged();
        }
    }

    public void addFooterView(View view){
        if(!mFooterViews.contains(view)){
            mFooterViews.add(view);
            notifyDataSetChanged();
        }
    }

    public void removeHeaderView(View view){
        if(mHeaderViews.contains(view)){
            mHeaderViews.remove(view);
            notifyDataSetChanged();
        }
    }

    public void removeFooterView(View view){
        if(mFooterViews.contains(view)){
            mFooterViews.remove(view);
            notifyDataSetChanged();
        }
    }
}
