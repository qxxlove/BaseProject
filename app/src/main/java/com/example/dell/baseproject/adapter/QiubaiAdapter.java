package com.example.dell.baseproject.adapter;

import android.content.Context;

import com.example.dell.baseproject.R;
import com.example.dell.baseproject.base.BaseAdapter;
import com.example.dell.baseproject.base.BaseViewHolder;
import com.example.dell.baseproject.bean.QiuBaiBean;

import java.util.List;

/**
 * 描述 ：
 * 作者：Created by SEELE on 2017/11/2.
 * 邮箱：123123@163.com
 */

public class QiubaiAdapter extends BaseAdapter<QiuBaiBean> {

    public QiubaiAdapter(Context context, List<QiuBaiBean> datas, int mLayoutId) {
        super(context, datas, mLayoutId);
    }

    @Override
    public void convert(BaseViewHolder holder, QiuBaiBean bean) {
         holder.setText(R.id.text_iamge_url,bean.getName_url());
         holder.setText(R.id.text_name, bean.getName());
         holder.setText(R.id.text_title,bean.getResultBean().getTitle());
        holder.setText(R.id.text_content,bean.getResultBean().getContent());
        holder.setText(R.id.text_content_url,bean.getResultBean().getImage_url());

    }
}
