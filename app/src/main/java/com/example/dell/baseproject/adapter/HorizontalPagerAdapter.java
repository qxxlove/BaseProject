package com.example.dell.baseproject.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.baseproject.R;
import com.example.dell.baseproject.utils.BaseUtils;
import com.gigamole.infinitecycleviewpager.VerticalInfiniteCycleViewPager;

import static com.example.dell.baseproject.utils.BaseUtils.setupItem;


/**
 * Created by GIGAMOLE on 7/27/16.
 */
public class HorizontalPagerAdapter extends PagerAdapter {

    private final BaseUtils.LibraryObject[] LIBRARIES = new BaseUtils.LibraryObject[]{
            new BaseUtils.LibraryObject(
                    R.mipmap.icon_one,
                    "Strategy"
            ),
            new BaseUtils.LibraryObject(
                    R.mipmap.icon_two,
                    "Design"
            ),
            new BaseUtils.LibraryObject(
                    R.mipmap.icon_threee,
                    "Development"
            ),
            new BaseUtils.LibraryObject(
                    R.mipmap.icon_four,
                    "Strategy"
            ),
            new BaseUtils.LibraryObject(
                    R.mipmap.icon_five,
                    "Strategy"
            ),
            new BaseUtils.LibraryObject(
                    R.mipmap.icon_six,
                    "Strategy"
            ),
            new BaseUtils.LibraryObject(
                    R.mipmap.icon_seven,
                    "Quality Assurance"
            )
    };

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private boolean mIsTwoWay;

    public HorizontalPagerAdapter(final Context context, final boolean isTwoWay) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mIsTwoWay = isTwoWay;
    }

    @Override
    public int getCount() {
        return mIsTwoWay ? 6 : LIBRARIES.length;
    }

    @Override
    public int getItemPosition(final Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final View view;
        if (mIsTwoWay) {
            view = mLayoutInflater.inflate(R.layout.layout_item_two_viewpager, container, false);

            final VerticalInfiniteCycleViewPager verticalInfiniteCycleViewPager =
                    (VerticalInfiniteCycleViewPager) view.findViewById(R.id.vicvp);
            verticalInfiniteCycleViewPager.setAdapter(
                    new VerticalPagerAdapter(mContext)
            );
            verticalInfiniteCycleViewPager.setCurrentItem(position);
        } else {
            view = mLayoutInflater.inflate(R.layout.layout_item_one_viewpagert, container, false);
            setupItem(view, LIBRARIES[position],container.getContext());
        }

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
