package com.function.luo.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.function.luo.pulltorefreshdemo.R;

import java.util.List;

/**
 * Created by luo on 2019/7/24.
 * BasicExample的Adapter
 */

public class BasicExampleAdapter extends RecyclerView.Adapter<BasicExampleAdapter.MyViewHolder> {


    private List<String> list;
    private final Context mContext;

    public BasicExampleAdapter(Context mContext, List<String> list) {
        this.list = list;
        this.mContext = mContext;
    }

    public void setList(List<String> listData) {
        this.list = listData;
    }
    @NonNull
    @Override
    public BasicExampleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //参数3：判断条件 true  1.是打气 2.添加到parent
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_example_basic,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BasicExampleAdapter.MyViewHolder holder, int position) {


        holder.tv_title.setText("Item："+ list.get(position));
        holder.tv_message.setText("这是一个条目信息："+ list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title;
        TextView tv_message;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_message = itemView.findViewById(R.id.tv_message);

        }
    }
}
