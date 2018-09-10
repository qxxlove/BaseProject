package com.example.dell.baseproject.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.dell.baseproject.R;
import com.example.dell.baseproject.bean.User;
import com.example.dell.baseproject.utils.PinyinUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2017/6/26.
 */
public class FilerAdapter extends BaseAdapter implements Filterable {


    private MyFilter myFilter;
    private List<User> userInfos;
    private Context context;
    private ArrayList<User> mOriginalValues;

    private final Object mLock = new Object();

    public FilerAdapter(Context context, List<User> userInfos) {
        this.context = context;
        this.userInfos = userInfos;
    }

    @Override
    public int getCount() {
        return userInfos.size() ;
    }

    @Override
    public User getItem(int position) {
        return  userInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_lsitview_search,
                    null);
            holder = new ViewHolder();

            holder.tv_nick = (TextView) view.findViewById(R.id.text_name);
            holder.tv_mobile = (TextView) view.findViewById(R.id.text_pass);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.tv_nick.setText(userInfos.get(position).getName());
        holder.tv_mobile.setText(userInfos.get(position).getPass());

        return view;
    }


    static class ViewHolder {

        TextView tv_nick;
        TextView tv_mobile;
    }



    public List<User> getUserInfos (){
        return  userInfos;
    }

    @Override
    public Filter getFilter() {
        if (myFilter == null) {
            myFilter = new MyFilter();
        }
        return myFilter;
    }

    class MyFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence prefix) {
            // 持有过滤操作完成之后的数据。该数据包括过滤操作之后的数据的值以及数量。 count:数量 values包含过滤操作之后的数据的值
            FilterResults results = new FilterResults();

            if (mOriginalValues == null) {
                synchronized (mLock) {
                    // 将list的用户 集合转换给这个原始数据的ArrayList
                    mOriginalValues = new ArrayList<User>(userInfos);
                }
            }
            if (prefix == null || prefix.length() == 0) {
                synchronized (mLock) {
                    ArrayList<User> list = new ArrayList<User>(
                            mOriginalValues);
                    results.values = list;
                    results.count = list.size();
                }
            } else {
                // 做正式的筛选
                String prefixString = prefix.toString().toLowerCase();

                // 声明一个临时的集合对象 将原始数据赋给这个临时变量
                final ArrayList<User> values = mOriginalValues;

                final int count = values.size();

                // 新的集合对象
                final ArrayList<User> newValues = new ArrayList<User>(
                        count);

                for (int i = 0; i < count; i++) {
                    // 如果姓名的前缀相符或者电话相符就添加到新的集合
                    final User value = (User) values.get(i);
                    Log.i("coder", "PinyinUtils.getAlpha(value.getUsername())"
                            + PinyinUtils.getFirstSpell(value.getName()));

                    if (PinyinUtils.getFirstSpell(value.getName()).startsWith(
                            prefixString)
                            || PinyinUtils.getFirstSpell(value.getPass()).startsWith(prefixString)
                            || value.getName().contains(prefixString)  || value.getPass().contains(prefixString)
                            ) {

                        newValues.add(value);
                    }
                }
                // 然后将这个新的集合数据赋给FilterResults对象
                results.values = newValues;
                results.count = newValues.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            // 重新将与适配器相关联的List重赋值一下
            userInfos = (List<User>) results.values;

            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }

    }


}
