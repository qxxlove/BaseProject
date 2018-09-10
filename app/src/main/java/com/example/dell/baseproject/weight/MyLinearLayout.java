package com.example.dell.baseproject.weight;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.baseproject.R;

/**
 * Created by DELL on 2017/6/24.
 */
public class MyLinearLayout extends LinearLayout implements GestureDetector.OnGestureListener {

    //定义手势检测器实例
    GestureDetector detector;
    private Context mContext;
    GestureDetector.OnGestureListener  onGestureListener;
    private TextView text_one;
    private TextView text_two;

    public MyLinearLayout(Context context) {
        this(context, null);
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //创建手势检测器
        detector = new GestureDetector(context,this);
        this.mContext = context;
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflate = inflater.inflate(R.layout.layout_view_linear, this);
        text_one = ((TextView) inflate.findViewById(R.id.text_test_one));
        text_two = ((TextView) inflate.findViewById(R.id.text_test_two));

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float minMove = 120;         //最小滑动距离
        float minVelocity = 0;      //最小滑动速度
        float beginX = e1.getX();
        float endX = e2.getX();
        float beginY = e1.getY();
        float endY = e2.getY();

        if(beginX-endX>minMove&& Math.abs(velocityX)>minVelocity){   //左滑
            Toast.makeText(mContext, velocityX + "左滑", Toast.LENGTH_SHORT).show();
        }else if(endX-beginX>minMove&& Math.abs(velocityX)>minVelocity){   //右滑
            Toast.makeText(mContext,velocityX+"右滑", Toast.LENGTH_SHORT).show();
        }else if(beginY-endY>minMove&& Math.abs(velocityY)>minVelocity){   //上滑
            Toast.makeText(mContext,velocityX+"上滑", Toast.LENGTH_SHORT).show();
            text_one.setVisibility(View.VISIBLE);
            text_two.setVisibility(View.VISIBLE);
           /* Animation animation = new TranslateAnimation(0,0, 0,-20);
            animation.setFillAfter(true);
            this.startAnimation(animation);*/
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this, "translationY", 0f, -100f);
            objectAnimator.setDuration(500);
          //  objectAnimator.setRepeatCount(1);
            objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
            objectAnimator.start();

        }else if(endY-beginY>minMove&& Math.abs(velocityY)>minVelocity){   //下滑
            Toast.makeText(mContext,velocityX+"下滑", Toast.LENGTH_SHORT).show();

            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this, "translationY", -100f, 0f);
            objectAnimator.setDuration(500);
            //  objectAnimator.setRepeatCount(1);
            objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
            objectAnimator.start();
            text_one.setVisibility(View.GONE);
            text_two.setVisibility(View.GONE);
        }

        return false;
    }
}
