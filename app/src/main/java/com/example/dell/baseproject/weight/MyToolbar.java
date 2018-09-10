package com.example.dell.baseproject.weight;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.TintTypedArray;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.baseproject.R;


/**
 * Created by DELL on 2017/6/12.
 */
public class MyToolbar extends Toolbar {

    private LayoutInflater layoutInflater ;
    private View view = null;
    private EditText edittxt;
    private TextView textView;
    private ImageView imageButton;

    public MyToolbar(Context context) {
        this(context,null);
    }

    public MyToolbar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        setContentInsetsRelative(10,10);    // 设置边距

        if (attrs != null) {
            final TintTypedArray tintTypedArray = TintTypedArray.obtainStyledAttributes(context, attrs,
                    R.styleable.MyToolbar, defStyleAttr, 0);

            final Drawable navIcon = tintTypedArray.getDrawable(R.styleable.MyToolbar_rightButton);
            Log.d("执行",navIcon+"");
            if (navIcon != null) {
                setRightButtonIcon(navIcon);
            }

            boolean isShowEdittext = tintTypedArray.getBoolean(R.styleable.MyToolbar_isShowEdittext,false);
            if (isShowEdittext) {
                showSearchView();
                hideTextViewTitle();
            }


            tintTypedArray.recycle();
        }


    }



    private void setRightButtonIcon(Drawable navIcon) {
        if (imageButton != null) {
            imageButton.setVisibility(View.VISIBLE);
            imageButton.setImageDrawable(navIcon);
        }
    }



    private void initView(Context context) {
        if (view == null) {
            layoutInflater =   LayoutInflater.from(context);

            view = layoutInflater.inflate(R.layout.view_toobar_layout,null);
            edittxt = ((EditText) view.findViewById(R.id.edittext_toolbar));
            textView = ((TextView) view.findViewById(R.id.text_title));
            imageButton = ((ImageView) view.findViewById(R.id.iamge_left));

            LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER_HORIZONTAL);
            addView(view,layoutParams);



        }

    }



    /**
     * 重写setTitle方法 （解决冲突（系统和自定义的））
     * @param resId
     */

    @Override
    public void setTitle(int resId) {
       // super.setTitle(resId);
        setTitle(getContext().getText(resId));
    }

    @Override
    public void setTitle(CharSequence title) {
       // super.setTitle(title);
        initView(getContext());           // 从源码的角度去学习，为什么此处要调用initView() 方法，应为该类继承Toolbar,会先执行父类的构造方法，执行完执行setTitle方法，而我们又重写了父类的setTitle方法，会发现textView控件是个空的，还没初始化
        if (textView != null){
            showTextViewTitle();
            textView.setText(title);
        }
    }




    public  void showSearchView() {
        if (edittxt != null) {
            edittxt.setVisibility(VISIBLE);
        }
    }
    public  void hideSearchView() {
        if (edittxt != null) {
            edittxt.setVisibility(GONE);
        }
    }

    public  void showTextViewTitle() {
        if (textView != null) {
            textView.setVisibility(VISIBLE);
        }
    }
    public  void hideTextViewTitle() {
        if (textView != null) {
            textView.setVisibility(GONE);
        }
    }

}
