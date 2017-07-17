package com.example.interviewdemo;

import android.app.Application;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.interviewdemo.adapter.ViewPagerAdapter;
import com.example.interviewdemo.fragment.CommentsFragment;
import com.example.interviewdemo.fragment.DetailsFragment;
import com.example.interviewdemo.view.FlowLayout;
import com.squareup.leakcanary.LeakCanary;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tablayout;
    private List<String> mTitles;
    private List<Fragment> mFragment;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private FlowLayout flowLayout;
    private String[] count = {"5KG/箱", "5KG/箱", "5KG/箱"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LeakCanary.install((Application) getApplicationContext());
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        //tablayout添加选项卡
        mTitles = new ArrayList<String>();
        mTitles.add("商品介绍");
        mTitles.add("商品评论");
        //添加fragment
        mFragment = new ArrayList<Fragment>();
        mFragment.add(new DetailsFragment());
        mFragment.add(new CommentsFragment());
        //tablayout和viewpager设置
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), mFragment, mTitles);
        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);
        tablayout.setTabsFromPagerAdapter(adapter);
        tablayout.setTabTextColors(Color.parseColor("#333333"), Color.parseColor("#ff6a00"));
        tablayout.setTabMode(TabLayout.MODE_FIXED);

//        for (int i = 0; i < count.length; i++) {
//            RadioButton button = new RadioButton(this);
//            button.setText(count[i]);
//            button.setButtonDrawable(null);
//            button.setPadding(10,10,10,10);
////            button.setBackground(ContextCompat.getDrawable(this,R.drawable.btnselect));
////            button.setBackgroundResource(R.drawable.btnselect);
//            if (i == 0) {
//                button.setEnabled(false);
//            }
//            flowLayout.addView(button);
//        }
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.vp_details);
        tablayout = (TabLayout) findViewById(R.id.tabs_details);
        flowLayout = (FlowLayout) findViewById(R.id.flowLayout);
    }
}
