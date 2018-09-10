package com.example.dell.baseproject.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.dell.baseproject.R;
import com.example.dell.baseproject.adapter.ListviewCheckSingleAdapter;
import com.example.dell.baseproject.bean.StudentBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListViewActivity extends AppCompatActivity {

    @BindView(R.id.listview_single)
    ListView listviewSingle;
    @BindView(R.id.activity_list_view)
    LinearLayout activityListView;

    private List<StudentBean>  list;
    private ListviewCheckSingleAdapter adapter;
    private  int mposition = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        ButterKnife.bind(this);
        initData();
        initClick();

    }

    private void initClick() {
        listviewSingle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (StudentBean bean : list) {//全部设为未选中
                    if (position == mposition){
                         if (list.get(position).isCheck()){
                             list.get(position).setCheck(false);
                         }else {
                             list.get(position).setCheck(true);
                         }
                        adapter.notifyDataSetChanged();
                        return;
                    }else {
                        bean.setCheck(false);
                    }

                }

                list.get(position).setCheck(true);//点击的设为选中
                mposition = position;
                adapter.notifyDataSetChanged();


            }
        });
    }

    private void initData() {
          list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
             if (mposition == i){
                 list.add(new StudentBean("学生"+i,true));
                 continue;
             }
             list.add(new StudentBean("学生"+i));
        }

        adapter = new ListviewCheckSingleAdapter(this,list,R.layout.item_lsitview_checkbox);
        listviewSingle.setAdapter(adapter);


    }
}
