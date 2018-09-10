package com.example.dell.baseproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.baseproject.R;
import com.example.dell.baseproject.bean.RecyclerData;

import java.util.List;

/**
 * 描述 ：
 * 作者：Created by SEELE on 2017/11/8.
 * 邮箱：123123@163.com
 */

public class BaseRecyclerAdapter extends RecyclerView.Adapter<BaseRecyclerAdapter.ViewHolder> {

    private List<RecyclerData>   list ;
    private Context mContext;

    public BaseRecyclerAdapter(List<RecyclerData> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_layout_two, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RecyclerData recyclerData = list.get(position);
        holder.name.setText(recyclerData.getName());
        holder.content.setText(recyclerData.getContent());
        holder.rt.setImageResource(R.mipmap.ic_launcher);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView rt;
        private TextView name;
        private TextView content;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.text_user_name);
            rt = (ImageView) itemView.findViewById(R.id.image_user_icon);
            content = (TextView) itemView.findViewById(R.id.text_user_content);

        }
    }


}
