package com.example.dell.baseproject.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.example.dell.baseproject.R;
import com.example.dell.baseproject.adapter.ViewCoolPagerAdapter;
import com.gigamole.navigationtabstrip.NavigationTabStrip;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewpagerCoolActivity extends AppCompatActivity {


    @BindView(R.id.nts)
    NavigationTabStrip nts;
    @BindView(R.id.vp_main)
    ViewPager vpMain;
    @BindView(R.id.activity_viewpager_cool)
    LinearLayout activityViewpagerCool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_cool);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        vpMain.setAdapter(new ViewCoolPagerAdapter(getSupportFragmentManager()));
        vpMain.setOffscreenPageLimit(2);

        final NavigationTabStrip navigationTabStrip = (NavigationTabStrip) findViewById(R.id.nts);
        navigationTabStrip.setTitles("圆圆", "冰冰");
        navigationTabStrip.setViewPager(vpMain);
    }
}
