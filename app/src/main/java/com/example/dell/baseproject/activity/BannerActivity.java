package com.example.dell.baseproject.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.example.dell.baseproject.R;
import com.example.dell.baseproject.adapter.CardPagerAdapter;
import com.example.dell.baseproject.bean.CardItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BannerActivity extends AppCompatActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.activity_banner)
    RelativeLayout activityBanner;

    private  CardPagerAdapter mCardAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        mCardAdapter = new CardPagerAdapter();
        mCardAdapter.addCardItem(new CardItem(R.string.title_1, R.string.text_1));
        mCardAdapter.addCardItem(new CardItem(R.string.title_2, R.string.text_1));
        mCardAdapter.addCardItem(new CardItem(R.string.title_3, R.string.text_1));
        mCardAdapter.addCardItem(new CardItem(R.string.title_4, R.string.text_1));

        viewPager.setAdapter(mCardAdapter);
    }

}
