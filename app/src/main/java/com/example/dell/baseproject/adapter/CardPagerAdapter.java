package com.example.dell.baseproject.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.baseproject.R;
import com.example.dell.baseproject.bean.CardItem;
import com.example.dell.baseproject.utils.BaseUtils;

import java.util.ArrayList;
import java.util.List;

public class CardPagerAdapter extends PagerAdapter implements CardAdapter {

    private List<CardView> mViews;
    private List<CardItem> mData;
    private float mBaseElevation;

    public CardPagerAdapter() {
        mData = new ArrayList<>();
    }

    public void addCardItem(CardItem item) {
        mData.add(item);
    }

    @Override
    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
       // return mViews.get(position);
        return  null ;
    }

    @Override
    public int getCount() {
        return mData.size();
    }



    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.item_virepager_adapter_layout, container, false);
        container.addView(view);
        bind(mData.get(position), view,container);
        CardView cardView = (CardView) view.findViewById(R.id.cardView);

      if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }

        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
       // mViews.set(position, cardView);*/
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        //mViews.set(position, null);
    }

    private void bind(CardItem item, View view, final ViewGroup container) {
        TextView titleTextView = (TextView) view.findViewById(R.id.titleTextView);
        TextView contentTextView = (TextView) view.findViewById(R.id.contentTextView);
        titleTextView.setText(item.getTitle());
        contentTextView.setText(item.getText());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseUtils.toast(container.getContext(),"点击事件");
            }
        });
        titleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseUtils.toast(container.getContext(),"text点击事件");
            }
        });
    }

}
