package com.example.dell.baseproject.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.baseproject.R;
import com.example.dell.baseproject.utils.BaseUtils;

import static com.example.dell.baseproject.utils.BaseUtils.setupItem;


/**
 * Created by GIGAMOLE on 7/27/16.
 */
public class VerticalPagerAdapter extends PagerAdapter {

    private final BaseUtils.LibraryObject[] TWO_WAY_LIBRARIES = new BaseUtils.LibraryObject[]{
            new BaseUtils.LibraryObject(
                    R.mipmap.img_one,
                    "Fintech"
            ),
            new BaseUtils.LibraryObject(
                    R.mipmap.img_two,
                    "Delivery"
            ),
            new BaseUtils.LibraryObject(
                    R.mipmap.img_three,
                    "Social network"
            ),
            new BaseUtils.LibraryObject(
                    R.mipmap.img_five,
                    "E-commerce"
            ),
            new BaseUtils.LibraryObject(
                    R.mipmap.img_four,
                    "Wearable"
            ),
            new BaseUtils.LibraryObject(
                    R.mipmap.img_six,
                    "Internet of things"
            ),
            new BaseUtils.LibraryObject(
                    R.mipmap.img_seven,
                    "Internet of things"
            ) ,
            new BaseUtils.LibraryObject(
                    R.mipmap.img_eight,
                    "Internet of things"
            )

    };

    private LayoutInflater mLayoutInflater;

    public VerticalPagerAdapter(final Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return TWO_WAY_LIBRARIES.length;
    }

    @Override
    public int getItemPosition(final Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final View view = mLayoutInflater.inflate(R.layout.layout_item_one_viewpagert, container, false);

        setupItem(view, TWO_WAY_LIBRARIES[position],container.getContext());

        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(final View view, final Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        container.removeView((View) object);
    }
}
