package com.example.dell.baseproject.weight;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.example.dell.baseproject.R;

/**
 * Created by DELL on 2017/6/12.
 */
public class MyPopupWindow extends PopupWindow {

    private Context mContext;

    private View view;
    private  Button btn_cancel;
    private  Button btn_close;
    private  Button btn_sure;

    private  int with_popup;
    private  int height_popup;

    public int getWith_popup() {
        return with_popup;
    }

    public void setWith_popup(int with_popup) {
        this.with_popup = with_popup;
    }

    public int getHeight_popup() {
        return height_popup;
    }

    public void setHeight_popup(int height_popup) {
        this.height_popup = height_popup;
    }

    public MyPopupWindow(Context mContext) {
        this.view = LayoutInflater.from(mContext).inflate(R.layout.popup_view_layout, null);

        btn_sure = (Button) view.findViewById(R.id.btn_sure);
        btn_cancel = (Button) view.findViewById(R.id.btn_cacle);
        btn_close = (Button) view.findViewById(R.id.btn_close);

        // 关闭按钮
        btn_close.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // 销毁弹出框
                dismiss();
            }
        });
        // 设置按钮监听
       // btn_sure.setOnClickListener(itemsOnClick);
        //btn_cancel.setOnClickListener(itemsOnClick);

        // 设置外部可点击
        this.setOutsideTouchable(true);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        this.view.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = view.findViewById(R.id.pop_layout).getTop();

                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });


    /* 设置弹出窗口特征 */
        // 设置视图
        this.setContentView(this.view);
        // 设置弹出窗体的宽和高   RelativeLayout.LayoutParams.WRAP_CONTENT
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);

        // 设置弹出窗体可点击
        this.setFocusable(true);

        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置弹出窗体的背景
        this.setBackgroundDrawable(dw);

        // 设置弹出窗体显示时的动画，从底部向上弹出
        this.setAnimationStyle(R.style.take_photo_anim);

    }

    public MyPopupWindow(Context mContext, View.OnClickListener itemsOnClick) {

    }

    public  View   getView (PopupWindow popupWindow,int id) {
        return  popupWindow.getContentView().findViewById(id);

    }

}
