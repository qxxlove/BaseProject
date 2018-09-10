package com.example.dell.baseproject.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.dell.baseproject.fragment.OneViewPagerFragement;
import com.example.dell.baseproject.fragment.TwoViewpagetFragement;

/**
 * Created by GIGAMOLE on 8/18/16.
 */
public class ViewCoolPagerAdapter extends FragmentStatePagerAdapter {

    private final static int COUNT = 2;

    private final static int HORIZONTAL = 0;
    private final static int TWO_WAY = 1;

    public ViewCoolPagerAdapter(final FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(final int position) {
        switch (position) {
            case TWO_WAY:
                return new TwoViewpagetFragement();
            case HORIZONTAL:
            default:
                return new OneViewPagerFragement();
        }
    }

    @Override
    public int getCount() {
        return COUNT;
    }
}
