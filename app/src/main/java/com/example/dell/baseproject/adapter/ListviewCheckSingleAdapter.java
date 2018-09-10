package com.example.dell.baseproject.adapter;

import android.content.Context;
import android.widget.CheckBox;

import com.example.dell.baseproject.R;
import com.example.dell.baseproject.base.BaseAdapter;
import com.example.dell.baseproject.base.BaseViewHolder;
import com.example.dell.baseproject.bean.StudentBean;

import java.util.List;

/**
 * 描述 ：
 * 作者：Created by SEELE on 2017/9/22.
 * 邮箱：123123@163.com
 */

public class ListviewCheckSingleAdapter  extends BaseAdapter<StudentBean> {




    public ListviewCheckSingleAdapter(Context context, List<StudentBean> datas, int mLayoutId) {
        super(context, datas, mLayoutId);
    }

    @Override
    public void convert(BaseViewHolder holder, StudentBean bean) {
        CheckBox checkBox = holder.getView(R.id.check_item_listview);
        holder.setText(R.id.text_item_name,bean.getName());
            if (bean.isCheck()) {
                checkBox.setChecked(true);
            }  else {
                checkBox.setChecked(false);
            }



    }
}
