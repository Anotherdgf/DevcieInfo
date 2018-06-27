package com.anotherdgf.deviceinfo.fragment;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.anotherdgf.deviceinfo.R;
import com.anotherdgf.deviceinfo.utils.EventUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by denggaofeng on 2018/6/25.
 */

public class RecyclerDemoFragment extends BaseFragment{
    private final static String TAG = "RecyclerDemoFragment";

    private RecyclerView recyclerView;
    private TextView mEmptyTextView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private JokeAdapter mAdapter;

    boolean isLoading;
    private List<Map<String, Object>> data = new ArrayList<>();

    public static RecyclerDemoFragment newInstance() {
        return new RecyclerDemoFragment();
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState){
        mEmptyTextView = (TextView) view.findViewById(R.id.empty_text_view);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.SwipeRefreshLayout);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
    }

    private void updateUI(){
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++){
            list.add("test #"+i);
        }

        if (list.size() > 0){
            mEmptyTextView.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
            if (null == mAdapter){
                mAdapter = new JokeAdapter(list);
                recyclerView.setAdapter(mAdapter);
            }else {
                mAdapter.setItems(list);
                mAdapter.notifyDataSetChanged();
            }
        }else {
            recyclerView.setVisibility(View.INVISIBLE);
            mEmptyTextView.setVisibility(View.VISIBLE);
        }

    }

    private class JokeHolder extends RecyclerView.ViewHolder{

        private TextView mTitleTextView;

        public JokeHolder(View itemView){
            super(itemView);
            mTitleTextView = (TextView)itemView.findViewById(R.id.list_item_title_view);
        }

        public void bindJokes(String str){
            mTitleTextView.setText(str);
        }
    }

    private class JokeAdapter extends RecyclerView.Adapter<JokeHolder>{

        private List<String> mList;

        public JokeAdapter(List<String> list) {
            mList = list;
        }

        @Override
        public JokeHolder onCreateViewHolder(ViewGroup parent,int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.item_joke_list,parent,false);
            return new JokeHolder(view);
        }

        @Override
        public void onBindViewHolder(JokeHolder holder,int position){
            String temp = mList.get(position);
            holder.bindJokes(temp);
        }

        @Override
        public int getItemCount(){
            return mList.size();
        }

        public void setItems(List<String> list){
            mList = list;
        }
    }

    @Override
    protected int getLayoutId(){
        return R.layout.fragment_recycler;
    }

}
