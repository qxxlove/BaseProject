package com.example.dell.baseproject.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dell.baseproject.R;
import com.example.dell.baseproject.bean.ActionBarStyle;
import com.example.dell.baseproject.weight.MyPopupWindow;

public class PopupWindowActivity extends BaseActivity {

    private Button button_show;
    private MyPopupWindow takePhotoPopWin ;

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.btn_cacle:
                   // System.out.println("btn_take_photo");
                    Toast.makeText(PopupWindowActivity.this,"取消",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_sure:
                  //  System.out.println("btn_pick_photo");
                    Toast.makeText(PopupWindowActivity.this,"确定",Toast.LENGTH_SHORT).show();

                    break;
            }
        }
    };


    @Override
    public int initContentID() {
        return 0;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_popup_window);
        setActionBarStyle(ActionBarStyle.ShowAll);
        setTitle("popupwindow");
        initView();
        initClick();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void execHttp() {

    }

    private void initClick() {
        button_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // takePhotoPopWin = new MyPopupWindow(PopupWindowActivity.this, onClickListener);
                 takePhotoPopWin = new MyPopupWindow(PopupWindowActivity.this);
               //  takePhotoPopWin.setHeight_popup(200);
                // takePhotoPopWin.setWith_popup(RelativeLayout.LayoutParams.MATCH_PARENT);
                //showAtLocation(View parent, int gravity, int x, int y)
                takePhotoPopWin.showAtLocation(findViewById(R.id.main_view), Gravity.BOTTOM, 0, 0);
            }
        });
    }

    private void initView() {
        button_show = ((Button) findViewById(R.id.button_popup_show_activity));
    }

}
