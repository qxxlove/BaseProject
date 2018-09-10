package com.example.dell.baseproject.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.example.dell.baseproject.R;

/**
 * Created by SEELE on 2017/7/4.
 */

public class VDHLinearLayout  extends LinearLayout {

    ViewDragHelper dragHelper;

    View dragView;
    View edgeDragView;
    View autoBackView;
    int autoBackViewOriginLeft;
    int autoBackViewOriginTop;


    public VDHLinearLayout(Context context) {
        this(context,null);
    }

    public VDHLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public VDHLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();


    }


    @Override
    public void computeScroll() {
        super.computeScroll();
        if (dragHelper.continueSettling(true))
        {
            invalidate();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        dragView = findViewById(R.id.dragView);
        edgeDragView = findViewById(R.id.edgeDragView);
        autoBackView = findViewById(R.id.autoBackView);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        autoBackViewOriginLeft = autoBackView.getLeft();
        autoBackViewOriginTop = autoBackView.getTop();
    }

    private void init() {
        //第一个为当前的ViewGroup，第二个为sensitivity，主要用于设置touchSlop：  传入的sensitivity越大，touchSlop值越小
        dragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
           // tryCaptureView：如果返回true表示捕获相关View，你可以根据第一个参数child决定捕获哪个View。
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return child == dragView || child == autoBackView;
            }

            // clampViewPositionVertical：计算child垂直方向的位置，top表示y轴坐标（相对于ViewGroup），默认返回0（如果不复写该方法）。这里，你可以控制垂直方向可移动的范围。
            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                return top;
            }

            // clampViewPositionHorizontal：与clampViewPositionVertical类似，只不过是控制水平方向的位置。
            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
              /*  if (left > getWidth() - child.getMeasuredWidth()) // 右侧边界
                {
                    left = getWidth() - child.getMeasuredWidth();
                }
                else if (left < 0) // 左侧边界
                {
                    left = 0;
                }*/
                return left;
            }

            // 当前被捕获的View释放之后回调
            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
                if (releasedChild == autoBackView)
                {
                    dragHelper.settleCapturedViewAt(autoBackViewOriginLeft, autoBackViewOriginTop);
                    invalidate();
                }
            }

            @Override
            public void onEdgeDragStarted(int edgeFlags, int pointerId) {
                dragHelper.captureChildView(edgeDragView, pointerId);
            }
        });

        // 设置左边缘可以被Drag
        dragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return dragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        dragHelper.processTouchEvent(event);
        return true;
    }




}
