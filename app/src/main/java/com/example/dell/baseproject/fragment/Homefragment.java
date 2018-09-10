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
import com.example.dell.baseproject.activity.DialogActivity;
import com.example.dell.baseproject.activity.ElseActivity;
import com.example.dell.baseproject.activity.PanelActivity;
import com.example.dell.baseproject.activity.PopupWIndowTwoActivity;
import com.example.dell.baseproject.activity.PopupWindowActivity;
import com.example.dell.baseproject.activity.ScrollActivity;
import com.example.dell.baseproject.activity.SearchViewActivity;
import com.example.dell.baseproject.activity.TimeActivity;
import com.example.dell.baseproject.activity.ViewGroupActivity;
import com.example.dell.baseproject.alipay.PayDemoActivity;
import com.example.dell.baseproject.utils.LogUtils;
import com.example.dell.baseproject.utils.TimeUtil;

/**
 * Created by DELL on 2017/6/10.
 */
public class Homefragment extends Fragment {

    private Button button_popupwindow;
    private Button button_toolbar;
    private Button btn_time;
    private Button button_scroll;
    private Button button_search;
    private Button  button_dialog;
    private Button button_slide;
    private Button button_viewGroup;
    private Button button_popup_two;
    private Button button_pay;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_hoem,null);
        button_popupwindow = ((Button) view.findViewById(R.id.button_popup_show));
        button_toolbar = ((Button) view.findViewById(R.id.button_toolbar_activity));
        btn_time = ((Button) view.findViewById(R.id.button_time));
        button_scroll = ((Button) view.findViewById(R.id.button_scroll_activity));
        button_search = ((Button) view.findViewById(R.id.button_search_activity));
        button_dialog = ((Button) view.findViewById(R.id.button_dialog_activity));
        button_slide = ((Button) view.findViewById(R.id.button_slide_activity));
        button_viewGroup = ((Button) view.findViewById(R.id.button_viewgroup_activity));
        button_popup_two = ((Button) view.findViewById(R.id.button_pop_two_activity));
        button_pay = ((Button) view.findViewById(R.id.button_pay_activity));

        initData();
        initCLick();
        return view;


    }

    private void initData() {
        String formatString = TimeUtil.getFormatString();
        String stringTime = TimeUtil.getStringTime(0);
        LogUtils.e("TAG1",stringTime);
        LogUtils.e("TAG3",System.currentTimeMillis()+"");
        LogUtils.e("TAG4",formatString);
/*

        00:00:00
        E/TAG3: 1498440773312
        E/TAG4: 2017-06-26 09:32:53
*/


    }

    private void initCLick() {
        button_popupwindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PopupWindowActivity.class);
                startActivity(intent);
            }
        });
        button_toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ElseActivity.class);
                startActivity(intent);
            }
        });
        btn_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /* String   result =  BaseUtils.getTimeDifference("2004-01-02 12:30:24","2004-01-03 9:30:24");
               BaseUtils.toast(getContext(),result);*/
                Intent intent = new Intent(getActivity(), TimeActivity.class);
                startActivity(intent);
            }
        });

        button_scroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ScrollActivity.class);
                startActivity(intent);
            }
        });
        button_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchViewActivity.class);
                startActivity(intent);
            }
        });
        button_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DialogActivity.class);
                startActivity(intent);
            }
        });
        button_slide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PanelActivity.class);
                startActivity(intent);
            }
        });
        button_viewGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ViewGroupActivity.class);
                startActivity(intent);
            }
        });
        button_popup_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PopupWIndowTwoActivity.class);
                startActivity(intent);
            }
        });
        button_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PayDemoActivity.class);
                startActivity(intent);
            }
        });



    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
