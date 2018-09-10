package com.example.dell.baseproject.recyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.baseproject.R;
import com.example.refreshview.CustomRefreshView;
import com.example.refreshview.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.List;

public class RecyclerListviewActivity extends AppCompatActivity {

    private CustomRefreshView refreshView;
    private List<String> data;
    private RecyclerViewAdapter adapter;
    private int pagerSize = 10;
    private int mm;

    private HeaderAndFooterWrapper mHeaderAndFooterWrapper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_listview);

        refreshView = (CustomRefreshView) findViewById(R.id.refresh_view);

        initHeaderAndFooter();

        data = new ArrayList<>();
        adapter = new RecyclerViewAdapter();
        refreshView.setAdapter(adapter);

        // 默认下拉刷新颜色
        refreshView.getSwipeRefreshLayout().setColorSchemeColors(getResources().getColor(android.R.color.holo_red_light));

        // 默认线性布局
        //  refreshView.getRecyclerView().setLayoutManager(new LinearLayoutManager(this));
        // refreshView.getRecyclerView().setLayoutManager(new GridLayoutManager(this,2));
           refreshView.getRecyclerView().setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));


        //设置自定义分割线需要自己写，默认有
         //refreshView.getRecyclerView().addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        //禁止下拉刷新
        //  refreshView.setRefreshEnable(false);

        //禁止加载更多
        //  refreshView.setLoadMoreEnable(false);

          //        设置emptyView
           //   TextView textView = new TextView(this);
          //    textView.setText("empty view");
           //    refreshView.setEmptyView(textView);

        //设置noMore
        // refreshView.onNoMore();

        //设置错误信息  调用onError后pager自行减1
        //refreshView.onError();



        /**
         * 模拟所有现象
         */
        refreshView.setOnLoadListener(new CustomRefreshView.OnLoadListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        data.clear();
                        for (int i = 0; i < pagerSize; i++) {
                            if (mm >= 2) {
                                data.add(String.valueOf(i));
                            }
                        }
                        ++mm;
                        //模拟无数据界面
                        if (mm == 1) {
                          /*  TextView textView = new TextView(RecyclerListviewActivity.this);
                            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(-1, -1);
                            textView.setLayoutParams(params);
                            textView.setGravity(Gravity.CENTER);
                            textView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    refreshView.setRefreshing(true);
                                }
                            });

                            textView.setText("自定义的无数据界面");
                            refreshView.setCreateView(textView);*/
                            refreshView.setEmptyView("暂无数据");
                            refreshView.complete();
                            return;
                        }
                        //模拟网络出错界面
                        if (mm == 2) {
                            refreshView.setErrorView();
                            refreshView.complete();
                            return;
                        }

                        refreshView.complete();
                        adapter.notifyDataSetChanged();
                    }
                }, 1000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < pagerSize; i++) {
                            data.add(String.valueOf(i));
                        }
                        if (data.size() > 20 && data.size() < 50) {
                            refreshView.onError();
                        } else {
                            if (data.size() < 70) {
                                refreshView.stopLoadingMore();
                            }
                        }
                        if (data.size() >= 70) {
                            refreshView.onNoMore();
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, 1000);
            }
        });

        //设置自动下拉刷新，切记要在recyclerView.setOnLoadListener()之后调用
        //因为在没有设置监听接口的情况下，setRefreshing(true),调用不到OnLoadListener
        refreshView.setRefreshing(true);


    }

    private void initHeaderAndFooter() {
        mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(adapter);

        TextView t1 = new TextView(this);
        t1.setText("Header 1");
        TextView t2 = new TextView(this);
        t2.setText("Header 2");
        mHeaderAndFooterWrapper.addHeaderView(t1);
        mHeaderAndFooterWrapper.addHeaderView(t2);
    }


    private class RecyclerViewAdapter extends RecyclerView.Adapter<ItemViewHolder> {

        @Override
        public int getItemCount() {
            return data == null ? 0 : data.size();
        }

        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(RecyclerListviewActivity.this).inflate(R.layout.item_layout_recycler, parent, false);
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ItemViewHolder holder, final int position) {
            holder.tv.setText("my position is " + position);
            holder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(RecyclerListviewActivity.this, "我是" + position + "号", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public ItemViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.tv);
        }
    }
}
