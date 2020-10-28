package com.hkrt.designmode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hkrt.designmode.sample3.WrapperRecyclerAdapter;
import com.hkrt.designmode.sample3.WrapperRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    WrapperRecyclerView recyclerView;

    List<Integer> mItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        mItems = new ArrayList<>(100);
        for(int i = 0;i < mItems.size();i++){
            mItems.add(i);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerAdapter mRealAdapter = new RecyclerAdapter();
        WrapperRecyclerAdapter adapter = new WrapperRecyclerAdapter(mRealAdapter);
        recyclerView.setAdapter(adapter);
        View headerView = LayoutInflater.from(this).inflate(R.layout.layout_header_view,recyclerView,false);
        View footerView = LayoutInflater.from(this).inflate(R.layout.layout_header_view,recyclerView,false);
        recyclerView.addHeaderView(headerView);
        recyclerView.addFooterView(footerView);
    }

    private class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View itemView = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_rv,parent,false);

            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
            holder.text.setText("position = " + mItems.get(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItems.remove(position);
                    notifyDataSetChanged();
                }
            });
        }


        @Override
        public int getItemCount() {
            return mItems.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{

            private TextView text;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                text = itemView.findViewById(R.id.text);
            }
        }
    }
}