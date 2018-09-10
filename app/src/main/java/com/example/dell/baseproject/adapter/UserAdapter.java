package com.example.dell.baseproject.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.dell.baseproject.R;
import com.example.dell.baseproject.bean.User;

/**
 * 描述 ：
 * 作者：Created by SEELE on 2017/11/1.
 * 邮箱：123123@163.com
 */

public class UserAdapter extends ListBaseAdapter<User> {


    public UserAdapter(Context context) {
        super(context);
    }



    @Override
    public int getLayoutId() {
        return R.layout.item_recyclerview_item;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        User item = mDataList.get(position);
        TextView text_name =  holder.getView(R.id.text_dot_name);
        text_name.setText(item.getName());
        TextView text_pass =  holder.getView(R.id.text_dot_address);
        text_name.setText(item.getPass());
    }
}
