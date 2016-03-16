package com.example.leashin.myapplication;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import net.tsz.afinal.FinalDb;

import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private Button mAddBtn;
    private FinalDb mFinalDb;

    private ArrayList<ItemModel> itemStrs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFinalDb = FinalDb.create(this);

        itemStrs = (ArrayList<ItemModel>) mFinalDb.findAllByWhere(ItemModel.class, "id < 5");
        if (itemStrs == null) {
            itemStrs = new ArrayList<>();
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.rcv);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter = new MyAdapter());

        mAddBtn = (Button) findViewById(R.id.btn_add);

        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemModel item = new ItemModel();
                item.setContent("" + new Date());
                itemStrs.add(item);
                mFinalDb.save(item);
                mAdapter.notifyItemInserted(itemStrs.size());
            }
        });
    }

    private class MyAdapter extends RecyclerView.Adapter {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            RecyclerView.ViewHolder holder = new MyHolder(LayoutInflater.from(MainActivity.this).
                    inflate(android.R.layout.simple_list_item_1, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((MyHolder) holder).tv.setText(itemStrs.get(position).getContent());
        }

        @Override
        public int getItemCount() {
            return itemStrs.size();
        }
    }

    private class MyHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public MyHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }
}
