package com.example.dell.baseproject.weight;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.example.dell.baseproject.R;
import com.example.dell.baseproject.utils.BaseUtils;

/**
 * Created by SEELE on 2017/7/10.
 */

public class MyPopupWindowTwo extends PopupWindow {

    private final ImageView imageView_down;
    private final LinearLayout linear_down;
    private Context mContext;
    //定义手势检测器实例
    GestureDetector detector;

    private View view;
    private Button btn_cancel;
    private  Button btn_close;
    private  Button btn_sure;






    public MyPopupWindowTwo(Context mContext, View.OnClickListener itemsOnClick) {

        this.view = LayoutInflater.from(mContext).inflate(R.layout.popup_view_two, null);

        btn_sure = (Button) view.findViewById(R.id.btn_sure);
        btn_cancel = (Button) view.findViewById(R.id.btn_cacle);
        btn_close = (Button) view.findViewById(R.id.btn_close);
        imageView_down = ((ImageView) view.findViewById(R.id.image_down));
        linear_down = ((LinearLayout) view.findViewById(R.id.linear_down));

        BaseUtils.setViewAnimaiton(mContext,imageView_down);

        // 关闭按钮
        btn_close.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // 销毁弹出框
                dismiss();
            }
        });
        // 设置按钮监听
        btn_sure.setOnClickListener(itemsOnClick);
        btn_cancel.setOnClickListener(itemsOnClick);
        imageView_down.setOnClickListener(itemsOnClick);
        linear_down.setOnClickListener(itemsOnClick);
        // 设置外部可点击
        this.setOutsideTouchable(false);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
       /* this.view.setOnTouchListener(new View.OnTouchListener() {

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
*/

    /* 设置弹出窗口特征 */
        // 设置视图
        this.setContentView(this.view);
        // 设置弹出窗体的宽和高
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        this.setWidth(BaseUtils.getScreeWith(mContext)-50);


        // 设置弹出窗体可点击
        this.setFocusable(true);

        // 实例化一个ColorDrawable颜色为半透明0xb0000000
        ColorDrawable dw = new ColorDrawable();
        // 设置弹出窗体的背景
        this.setBackgroundDrawable(dw);

        // 设置弹出窗体显示时的动画，从底部向上弹出
        this.setAnimationStyle(R.style.take_photo_anim);

    }


    public  View   getView (PopupWindow popupWindow,int id) {
        return  popupWindow.getContentView().findViewById(id);

    }

}
