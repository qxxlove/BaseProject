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
import com.example.dell.baseproject.activity.ActivityContentActivity;
import com.example.dell.baseproject.activity.ListViewActivity;
import com.example.dell.baseproject.activity.ScrollViewActivity;
import com.example.dell.baseproject.https.HttpsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by DELL on 2017/6/10.
 */
public class Minefragment extends Fragment {


    @BindView(R.id.btn_listview_single)
    Button btnListviewSingle;
    Unbinder unbinder;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.btn_listview_single,R.id.btn_https_internet,R.id.btn_srollvoiew_activity,R.id.btn_activity_base_content})
    public  void  initClick (View view) {
        switch (view.getId()){
            case R.id.btn_listview_single :
                Intent intent = new Intent(getActivity(), ListViewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_https_internet :
                Intent intent1 = new Intent(getActivity(), HttpsActivity.class);
                startActivity(intent1);
                break;
            case  R.id.btn_srollvoiew_activity:
                Intent intent2 = new Intent(getActivity(), ScrollViewActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_activity_base_content:
                Intent intent3 = new Intent(getActivity(), ActivityContentActivity.class);
                startActivity(intent3);
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
