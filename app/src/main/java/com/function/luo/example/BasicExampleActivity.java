package com.function.luo.example;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.function.luo.adapter.BasicExampleAdapter;
import com.function.luo.pulltorefreshdemo.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by luo on 2019/7/24.
 *
 * 这是一个标准的上拉刷新，下拉加载更多
 */

public class BasicExampleActivity extends Activity {


    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    List<String> list = new ArrayList<String>();
    BasicExampleAdapter adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_basic);
        ButterKnife.bind(this);


        init();
        initData();

    }

    private void init() {


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvList.setLayoutManager(linearLayoutManager);



         adapter = new BasicExampleAdapter(this,list);
        rvList.setAdapter(adapter);


        //开启自动加载功能（非必须）
        refreshLayout.setEnableAutoLoadMore(true);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {



                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshData();
                        adapter.notifyDataSetChanged();
                        refreshLayout.finishRefresh();
                        refreshLayout.resetNoMoreData();//setNoMoreData(false);
                    }
                }, 2000);
            }
        });


        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (list.size() > 30) {
                            Toast.makeText(getApplication(), "数据全部加载完毕", Toast.LENGTH_SHORT).show();
                            refreshLayout.finishLoadMoreWithNoMoreData();//将不会再次触发加载更多事件
                        } else {
                            loadMore();
                            adapter.notifyDataSetChanged();
                            refreshLayout.finishLoadMore();
                        }
                    }
                }, 2000);


            }
        });



        //触发自动刷新
        refreshLayout.autoRefresh();
    }


    /**
     * 加载更多数据
     */
    private void loadMore() {
        for (int i = 30; i < 50 ; i++) {
            list.add(String.valueOf(i));
        }

        adapter.setList(list);
    }



    /**
     * 初始化数据
     */
    public void initData(){
        for (int i = 0; i < 30 ; i++) {
            list.add(String.valueOf(i));
        }

        adapter.setList(list);
    }


    /**
     * 刷新数据
     */
    public void refreshData(){

        adapter.setList(list);
    }
    public static void launch(Context mContext) {
        Intent intent = new Intent(mContext,BasicExampleActivity.class);
        mContext.startActivity(intent);
    }
}
