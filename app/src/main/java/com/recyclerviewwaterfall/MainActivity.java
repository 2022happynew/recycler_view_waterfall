package com.recyclerviewwaterfall;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> mDatas;
    private MainAdapter mainAdapter;
    private List<Integer> mHeights;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        initData();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mainAdapter = new MainAdapter();
        recyclerView.setAdapter(mainAdapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
    }

    private void initData() {
        mDatas = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }

    class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

        MainAdapter() {
            mHeights = new ArrayList<Integer>();
            for (int i = 0; i < mDatas.size(); i++) {
                mHeights.add((int) (100 + Math.random() * 300));
            }
        }

        @Override
        public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MainViewHolder mainViewHolder = new MainViewHolder(LayoutInflater.from(MainActivity.this).inflate(R
                    .layout.item_layout, parent, false));

            return mainViewHolder;
        }

        @Override
        public void onBindViewHolder(MainViewHolder holder, int position) {
            ViewGroup.LayoutParams lp = holder.textView.getLayoutParams();
            lp.height = mHeights.get(position);
            holder.textView.setLayoutParams(lp);
            holder.textView.setText(mDatas.get(position));
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class MainViewHolder extends RecyclerView.ViewHolder {

            TextView textView;

            public MainViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.id_num);
            }
        }
    }
}
