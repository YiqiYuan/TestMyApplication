package com.example.interviewdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;

/**
 * Created by Js on 2016/4/2.
 */
public class PagingChildWebView extends WebView {
    public float oldY;
    private int t;

    public PagingChildWebView(Context context) {
        this(context,null);
    }

    public PagingChildWebView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PagingChildWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                getParent().getParent().getParent().requestDisallowInterceptTouchEvent(true);
                oldY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                getParent().getParent().getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                float Y = event.getY();
                float Ys = Y - oldY;
                if (Ys >0 &&t==0) {
                    getParent().getParent().getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        this.t = t;
        super.onScrollChanged(l, t, oldl, oldt);
    }
}
