package com.example.interviewdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by Js on 2016/4/2.
 */
public class PagingChildLinerLayout extends LinearLayout {
    public PagingChildLinerLayout(Context context) {
        this(context,null);
    }

    public PagingChildLinerLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PagingChildLinerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
