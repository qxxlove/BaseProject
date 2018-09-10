package com.example.dell.baseproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.baseproject.R;
import com.example.dell.baseproject.adapter.HorizontalPagerAdapter;
import com.example.dell.baseproject.utils.BaseUtils;
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;

/**
 * 描述 ：
 * 作者：Created by SEELE on 2017/11/10.
 * 邮箱：123123@163.com
 */

public class TwoViewpagetFragement extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v   = BaseUtils.inflate(container.getContext(), R.layout.fragment_one_viewpager);

        return v;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final HorizontalInfiniteCycleViewPager horizontalInfiniteCycleViewPager =
                (HorizontalInfiniteCycleViewPager) view.findViewById(R.id.hicvp);
        horizontalInfiniteCycleViewPager.setAdapter(new HorizontalPagerAdapter(getContext(), true));
    }
}
