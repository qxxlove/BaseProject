package com.example.dell.baseproject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.baseproject.R;
import com.example.dell.baseproject.activity.BannerActivity;
import com.example.dell.baseproject.activity.ConstraintLayoutActivity;
import com.example.dell.baseproject.activity.DownloadActivity;
import com.example.dell.baseproject.activity.RetrofitLoginActivity;
import com.example.dell.baseproject.activity.RetrofitMApDotActivity;
import com.example.dell.baseproject.activity.ViewpagerCoolActivity;
import com.example.dell.baseproject.activity.ViewpagerTwoActivity;
import com.example.dell.baseproject.coordnitor.CoordinatorLayoutActivity;
import com.example.dell.baseproject.coordnitor.CoordinatorTwoActivity;
import com.example.dell.baseproject.mvp.biz.mmmmvp.v.LoginActivity;
import com.example.dell.baseproject.recyclerview.QiuBaiJsoupActivity;
import com.example.dell.baseproject.recyclerview.RecyclerListviewActivity;
import com.example.dell.baseproject.recyclerview.RecyclerViewTwoActivity;
import com.example.dell.baseproject.rxjava.RxjavaActivity;
import com.example.dell.baseproject.ui.nestedscroll.NestedScrollOneActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Created by DELL on 2017/6/10.
 */
public class TypeFragment extends Fragment {

    @BindView(R.id.text_login_mvp)
    TextView textLoginMvp;
    Unbinder unbinder;
    @BindView(R.id.text_rx_java)
    TextView textRxJava;
    @BindView(R.id.text_retrofit)
    TextView textRetrofit;
    @BindView(R.id.text_download)
    TextView textDownload;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_type, null);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.text_login_mvp, R.id.text_rx_java, R.id.text_retrofit,R.id.text_download,R.id.text_retrofit_login,R.id.text_NestedScroll
    ,R.id.text_recycler_one,R.id.text_jsoup,R.id.text_constraintlayout,R.id.text_coordinatorlayout,R.id.text_coordinatorlayout_two
    ,R.id.text_banner_activity,R.id.text_recycler_two,R.id.viewpager_cool_activity,R.id.viewpager_two_activity})
    public void initClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.text_login_mvp:
                intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.text_rx_java:
                intent = new Intent(getActivity(), RxjavaActivity.class);
                startActivity(intent);
                break;
            case R.id.text_retrofit:
                intent = new Intent(getActivity(), RetrofitMApDotActivity.class);
                startActivity(intent);
                break;
            case R.id.text_download :
                intent = new Intent(getActivity(), DownloadActivity.class);
                startActivity(intent);
                break;
            case R.id.text_retrofit_login:
                intent = new Intent(getActivity(), RetrofitLoginActivity.class);
                startActivity(intent);
                break;
            case R.id.text_NestedScroll :
                intent = new Intent(getActivity(), NestedScrollOneActivity.class);
                startActivity(intent);
                break;
            case R.id.text_recycler_one :
                intent = new Intent(getActivity(), RecyclerListviewActivity.class);
                startActivity(intent);
                break;
            case R.id.text_jsoup:
                intent = new Intent(getActivity(), QiuBaiJsoupActivity.class);
                startActivity(intent);
                break;
            case R.id.text_constraintlayout:
                intent = new Intent(getActivity(), ConstraintLayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.text_coordinatorlayout:
                intent = new Intent(getActivity(), CoordinatorLayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.text_coordinatorlayout_two:
                intent = new Intent(getActivity(), CoordinatorTwoActivity.class);
                startActivity(intent);
                break;
            case R.id.text_banner_activity:
                intent = new Intent(getActivity(), BannerActivity.class);
                startActivity(intent);
                break;
            case R.id.text_recycler_two:
                intent = new Intent(getActivity(), RecyclerViewTwoActivity.class);
                startActivity(intent);
                break;
            case R.id.viewpager_cool_activity:
                intent = new Intent(getActivity(), ViewpagerCoolActivity.class);
                startActivity(intent);
                break;
            case R.id.viewpager_two_activity :
                intent = new Intent(getActivity(), ViewpagerTwoActivity.class);
                startActivity(intent);
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
