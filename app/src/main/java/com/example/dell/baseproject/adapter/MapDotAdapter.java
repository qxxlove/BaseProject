package com.example.dell.baseproject.adapter;

import android.content.Context;

import com.example.dell.baseproject.R;
import com.example.dell.baseproject.base.BaseAdapter;
import com.example.dell.baseproject.base.BaseViewHolder;
import com.example.dell.baseproject.bean.MapDotInfoBean;

import java.util.List;

/**
 * 描述 ：
 * 作者：Created by SEELE on 2017/10/20.
 * 邮箱：123123@163.com
 */

public class MapDotAdapter extends BaseAdapter<MapDotInfoBean.ResultBean> {

    public MapDotAdapter(Context context, List<MapDotInfoBean.ResultBean> datas, int mLayoutId) {
        super(context, datas, mLayoutId);
    }

    @Override
    public void convert(BaseViewHolder holder, MapDotInfoBean.ResultBean bean) {
           holder.setText(R.id.text_dot_name,bean.getDot_name());
          holder.setText(R.id.text_dot_address,bean.getDot_address()) ;
    }


}
