package com.example.dell.baseproject.ui.nestedscroll;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dell.baseproject.R;
import com.example.dell.baseproject.adapter.UserAdapter;
import com.example.dell.baseproject.bean.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * description:
 * autour: TMM
 * date: 2017/11/1 16:48
 * update: 2017/11/1
 * version:
 * 1. 在onMeasure方法中需要重新测量自身的高度，在原本高度的基础上加上edv_header的高度，这样我们在向上滑的时候才不会滑出一片空白来。
 2. 在onNestedPreScroll方法中通过ViewCompat.offsetTopAndBottom()来移动edv_content。这只是移动方法之一，还有其他方法，比如通过设置layoutParams。
 3. 通过ViewTreeObserver的OnPreDrawListener来获取edv_content位置改变的信息(因为位置改变就必然涉及到重绘)。定义一个Listener接口并对外提供setListener方法，方便ElemeDetailView的使用者监听edv_content的位置改变。
 *
 *
 * 参考： http://blog.csdn.net/al4fun/article/details/53888990
 */


public class NestedScrollTwoActivity extends AppCompatActivity {

    @BindView(R.id.edv_title)
    TextView edvTitle;
    @BindView(R.id.edv_header)
    TextView edvHeader;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.edv_content)
    LinearLayout edvContent;
    @BindView(R.id.activity_nested_scroll_two)
    RecyclerDatailView activityNestedScrollTwo;

    private List<User>   list;
    private UserAdapter userAdapter ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_scroll_two);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        list = new ArrayList<>();
        list.add(new User("小乔","123456"));
        list.add(new User("小话","123456123"));
        list.add(new User("小司","123456344"));
        list.add(new User("小男","123456775"));
        list.add(new User("小本","123456878"));
        list.add(new User("小啊","12345655"));
        list.add(new User("小吧","12345685"));

        userAdapter = new UserAdapter(this);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(userAdapter);

        activityNestedScrollTwo.setListener(new RecyclerDatailView.Listener() {
            @Override
            public void onContentPostionChanged(float fraction) {
                edvTitle.setAlpha(1 - fraction);
            }
        });

    }
}
