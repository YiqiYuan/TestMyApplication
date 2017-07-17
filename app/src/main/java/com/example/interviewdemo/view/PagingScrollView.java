package com.example.interviewdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * Created by Js on 2016/4/2.
 */
public class PagingScrollView extends ScrollView {
    private int mScreenHeight;
    private boolean isSetted = false;
    private boolean ispageOne = true;
    private PagingChildScrollView pagingChildScrollView;
    private PagingChildLinerLayout pagingChildLinerLayout;
    private PageOpen PageOpen;

    public PagingScrollView(Context context) {
        this(context, null);
    }

    public PagingScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PagingScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!isSetted) {
            //得到里边的控件
            setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
                    getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));
            mScreenHeight = getMeasuredHeight();
            final LinearLayout warpper = (LinearLayout) getChildAt(0);
            pagingChildScrollView = (PagingChildScrollView) warpper.getChildAt(0);
            pagingChildLinerLayout = (PagingChildLinerLayout) warpper.getChildAt(1);
            //设置两个子view的高度为屏幕的高度
            pagingChildScrollView.getLayoutParams().height = mScreenHeight;
            pagingChildLinerLayout.getLayoutParams().height = mScreenHeight;
            isSetted = true;
        }else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    public static int getDefaultSize(int size, int measureSpec) {
        int result = size;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                result = size;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
        }
        return result;
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            this.scrollTo(0, 0);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                //隐藏在左边的距离
                int scrollY = getScrollY();
                int creteria = mScreenHeight / 5;//滑动多少距离
                if (ispageOne) {
                    if (scrollY <= creteria) {
                        //显示菜单
                        smoothScrollTo(0);
                    } else {
                        //隐藏菜单
                        if (PageOpen != null) {
                            PageOpen.PageOpenChange(true);
                        }
                        smoothScrollTo(mScreenHeight);
                        this.setFocusable(false);
                        ispageOne = false;
                    }
                } else {
                    int scrollpadding = mScreenHeight - scrollY;
                    if (scrollpadding >= creteria) {
                        smoothScrollTo(0);
                        ispageOne = true;
                        if (PageOpen != null) {
                            PageOpen.PageOpenChange(false);
                        }
                    } else {
                        smoothScrollTo(mScreenHeight);
                    }

                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    private void smoothScrollTo(final int y) {
        post(new Runnable() {
            @Override
            public void run() {
                smoothScrollTo(0, y);
            }
        });
    }

    public void setPageOpen(PagingScrollView.PageOpen pageOpen) {
        PageOpen = pageOpen;
    }

    public interface PageOpen {
        void PageOpenChange(boolean isOpen);
    }
}
