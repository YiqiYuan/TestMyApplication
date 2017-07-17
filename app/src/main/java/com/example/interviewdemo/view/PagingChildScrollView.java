package com.example.interviewdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by Js on 2016/4/2.
 */
public class PagingChildScrollView extends ScrollView {
    private int mScreenHeight;
    public float oldY;
    private int t;
    private boolean isSetted = false;

    public PagingChildScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PagingChildScrollView(Context context) {
        this(context,null);
    }

    public PagingChildScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!isSetted) {
            mScreenHeight = MeasureSpec.getSize(heightMeasureSpec);
            isSetted = true;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                //手指按下的时候，获得滑动事件，也就是让顶级scrollview失去滑动事件
                getParent().getParent().requestDisallowInterceptTouchEvent(true);
                //并且记录Y点值
                oldY = ev.getY();
                break;
            case MotionEvent.ACTION_UP:
                getParent().getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                //在滑动的时候获得当前值，并计算得到YS,用来判断是向上滑动还是向下滑动
                float Y = ev.getY();
                float Ys = Y - oldY;
                //得到scrollview里面空间的高度
                int childHeight = this.getChildAt(0).getMeasuredHeight();
                //子控件高度减去scrollview向上滑动的距离
                int padding = childHeight - t;
                //Ys<0表示手指正在向上滑动，padding==mScreenHeight表示本scrollview已经滑动到了底部
                if (Ys <0 &&padding==mScreenHeight) {
                    //让顶级的scrollview重新获得滑动事件
                    getParent().getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        //t表示本scrollview向上滑动的距离
        this.t = t;
        super.onScrollChanged(l, t, oldl, oldt);
    }
}
