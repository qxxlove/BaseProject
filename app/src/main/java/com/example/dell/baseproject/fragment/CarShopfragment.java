package com.example.dell.baseproject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dell.baseproject.R;
import com.example.dell.baseproject.activity.BUtterknifeActivity;
import com.example.dell.baseproject.activity.BaiDuMapActivity;
import com.example.dell.baseproject.activity.GridViewActivity;
import com.example.dell.baseproject.activity.LogUtilsActivity;
import com.example.dell.baseproject.activity.RecyclerListViewActivity;
import com.example.dell.baseproject.activity.ShraedActivity;
import com.example.dell.baseproject.activity.ThreadPoolExecutorActivity;
import com.example.dell.baseproject.mvp.biz.mvc.MvcActivity;
import com.example.dell.baseproject.utils.SharePreUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Created by DELL on 2017/6/10.
 */
public class CarShopfragment extends Fragment {


    @BindView(R.id.button_listview_activity)
    Button buttonListviewActivity;
    @BindView(R.id.button_butterknife_activity)
    Button buttonButterknifeActivity;
    Unbinder unbinder;
    @BindView(R.id.button_sharedPrefernces_activity)
    Button buttonSharedPreferncesActivity;
    @BindView(R.id.button_baidumap_activity)
    Button buttonBaidumapActivity;
    @BindView(R.id.button_mvp_activity)
    Button buttonMvpActivity;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car, null);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        SharePreUtils.put("string", "xxxxxx string类型");
        SharePreUtils.put("int", 10);
        SharePreUtils.put("long", 1026565451L);
        SharePreUtils.put("float", 2.123456789f);
        SharePreUtils.put("double", "2.123456789");
        SharePreUtils.put("boolean", false);


    }

    @OnClick({R.id.button_listview_activity, R.id.button_butterknife_activity, R.id.button_sharedPrefernces_activity,
            R.id.button_baidumap_activity,R.id.button_mvp_activity,R.id.button_log_activity,R.id.button_gridview_activity,
            R.id.button_Thread_Pool_activity})
    public void initclick(View view) {
        switch (view.getId()) {
            case R.id.button_listview_activity:
                Intent intent = new Intent(getActivity(), RecyclerListViewActivity.class);
                startActivity(intent);
                break;
            case R.id.button_butterknife_activity:
                Intent intent1 = new Intent(getActivity(), BUtterknifeActivity.class);
                startActivity(intent1);
                break;
            case R.id.button_sharedPrefernces_activity:
                Intent intent2 = new Intent(getActivity(), ShraedActivity.class);
                startActivity(intent2);
                break;
            case R.id.button_baidumap_activity:
                Intent intent3 = new Intent(getActivity(), BaiDuMapActivity.class);
                startActivity(intent3);
                break;
            case R.id.button_mvp_activity:
                Intent intent4 = new Intent(getActivity(), MvcActivity.class);
                startActivity(intent4);
                break;
            case R.id.button_log_activity :
                Intent intent5 = new Intent(getActivity(), LogUtilsActivity.class);
                startActivity(intent5);
                break;
            case R.id.button_gridview_activity:
                Intent intent6 = new Intent(getActivity(), GridViewActivity.class);
                startActivity(intent6);
                break;
            case R.id.button_Thread_Pool_activity:
                Intent intent7 = new Intent(getActivity(), ThreadPoolExecutorActivity.class);
                startActivity(intent7);
                break;
             default:

        }


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
