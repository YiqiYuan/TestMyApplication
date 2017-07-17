package com.example.interviewdemo.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.interviewdemo.BuildConfig;
import com.example.interviewdemo.R;
import com.example.interviewdemo.adapter.CommentListAdapter;
import com.example.interviewdemo.bean.CommentBean;
import com.example.interviewdemo.view.PagingChildListView;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/1.
 */
public class CommentsFragment extends BaseFragment implements View.OnClickListener {

    private PagingChildListView listView;
    private List<CommentBean.DataBean> mList;
    private TextView textViewComment;
    //构建JSON假数据
    private String json="{\n" +
            "  \"Data\": [\n" +
            "   {\n" +
            "     \"portrait\":\"http://imgsrc.baidu.com/forum/pic/item/6e551a4c510fd9f93ae5b75e232dd42a2934a441.jpg\",\n" +
            "     \"name\":\"我是布偶我怕谁\",\n" +
            "     \"starlevel\":3,\n" +
            "     \"title\":\"很好吃很好吃\",\n" +
            "     \"date\":\"2016-03-31 01:26\",\n" +
            "     \"conten\":\"这里的车厘子呈暗红色,果实硕大,坚实而多汁,入口甜美.每年,美国的车厘子只有两个月的成熟时间\",\n" +
            "     \"commentimage\":[]\n" +
            "   },\n" +
            "   {\n" +
            "     \"portrait\":\"http://imgsrc.baidu.com/forum/pic/item/6e551a4c510fd9f93ae5b75e232dd42a2934a441.jpg\",\n" +
            "     \"name\":\"我是布偶我怕谁\",\n" +
            "     \"starlevel\":3,\n" +
            "     \"title\":\"很好吃很好吃\",\n" +
            "     \"date\":\"2016-03-31 01:26\",\n" +
            "     \"conten\":\"这里的车厘子呈暗红色,果实硕大,坚实而多汁,入口甜美.每年,美国的车厘子只有两个月的成熟时间\",\n" +
            "     \"commentimage\":[]\n" +
            "   },\n" +
            "   {\n" +
            "     \"portrait\":\"http://imgsrc.baidu.com/forum/pic/item/6e551a4c510fd9f93ae5b75e232dd42a2934a441.jpg\",\n" +
            "     \"name\":\"我是布偶我怕谁\",\n" +
            "     \"starlevel\":3,\n" +
            "     \"title\":\"很好吃很好吃\",\n" +
            "     \"date\":\"2016-03-31 01:26\",\n" +
            "     \"conten\":\"这里的车厘子呈暗红色,果实硕大,坚实而多汁,入口甜美.每年,美国的车厘子只有两个月的成熟时间\",\n" +
            "     \"commentimage\":[\"http://imgsrc.baidu.com/forum/pic/item/6e551a4c510fd9f93ae5b75e232dd42a2934a441.jpg\",\"http://imgsrc.baidu.com/forum/pic/item/6e551a4c510fd9f93ae5b75e232dd42a2934a441.jpg\"]\n" +
            "   },\n" +
            "   {\n" +
            "     \"portrait\":\"http://imgsrc.baidu.com/forum/pic/item/6e551a4c510fd9f93ae5b75e232dd42a2934a441.jpg\",\n" +
            "     \"name\":\"我是布偶我怕谁\",\n" +
            "     \"starlevel\":3,\n" +
            "     \"title\":\"很好吃很好吃\",\n" +
            "     \"date\":\"2016-03-31 01:26\",\n" +
            "     \"conten\":\"这里的车厘子呈暗红色,果实硕大,坚实而多汁,入口甜美.每年,美国的车厘子只有两个月的成熟时间\",\n" +
            "     \"commentimage\":[]\n" +
            "   },\n" +
            "   {\n" +
            "     \"portrait\":\"http://imgsrc.baidu.com/forum/pic/item/6e551a4c510fd9f93ae5b75e232dd42a2934a441.jpg\",\n" +
            "     \"name\":\"我是布偶我怕谁\",\n" +
            "     \"starlevel\":3,\n" +
            "     \"title\":\"很好吃很好吃\",\n" +
            "     \"date\":\"2016-03-31 01:26\",\n" +
            "     \"conten\":\"这里的车厘子呈暗红色,果实硕大,坚实而多汁,入口甜美.每年,美国的车厘子只有两个月的成熟时间\",\n" +
            "     \"commentimage\":[]\n" +
            "   },\n" +
            "   {\n" +
            "     \"portrait\":\"http://imgsrc.baidu.com/forum/pic/item/6e551a4c510fd9f93ae5b75e232dd42a2934a441.jpg\",\n" +
            "     \"name\":\"我是布偶我怕谁\",\n" +
            "     \"starlevel\":3,\n" +
            "     \"title\":\"很好吃很好吃\",\n" +
            "     \"date\":\"2016-03-31 01:26\",\n" +
            "     \"conten\":\"这里的车厘子呈暗红色,果实硕大,坚实而多汁,入口甜美.每年,美国的车厘子只有两个月的成熟时间\",\n" +
            "     \"commentimage\":[]\n" +
            "   },\n" +
            "   {\n" +
            "     \"portrait\":\"http://imgsrc.baidu.com/forum/pic/item/6e551a4c510fd9f93ae5b75e232dd42a2934a441.jpg\",\n" +
            "     \"name\":\"我是布偶我怕谁\",\n" +
            "     \"starlevel\":3,\n" +
            "     \"title\":\"很好吃很好吃\",\n" +
            "     \"date\":\"2016-03-31 01:26\",\n" +
            "     \"conten\":\"这里的车厘子呈暗红色,果实硕大,坚实而多汁,入口甜美.每年,美国的车厘子只有两个月的成熟时间\",\n" +
            "     \"commentimage\":[]\n" +
            "   }\n" +
            "  ]\n" +
            "  \n" +
            "}";
    private CommentListAdapter adapter;


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        final View view = inflater.inflate(R.layout.fragment_comments,container,false);
        listView = (PagingChildListView) view.findViewById(R.id.lv_comments);
        textViewComment = (TextView) view.findViewById(R.id.tv_comments);
        return view;
    }

    @Override
    protected void initData() {
        mList = new ArrayList<CommentBean.DataBean>();
        Gson gson = new Gson();
        CommentBean commentBean = gson.fromJson(json, CommentBean.class);
         mList = commentBean.getData();
        adapter = new CommentListAdapter(mActivity,mList);
        listView.setAdapter(adapter);


    }

    @Override
    protected void initListener() {
        textViewComment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.tv_comments:
                showPopupwindow(textViewComment);
                break;
        }

    }

    private void showPopupwindow(TextView view) {
        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(mActivity).inflate(
                R.layout.popupwindow_layout, null);
        final PopupWindow popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Log.i("mengdd", "onTouch : ");

                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });
        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(ContextCompat.getDrawable(getContext(),R.drawable.popupwindow));
        // 设置好参数之后再show
        final float scale = mActivity.getResources().getDisplayMetrics().density;
          int y=(int) (61 * scale + 0.5f);
        popupWindow.showAtLocation(view,Gravity.BOTTOM,0,y);
        // 设置按钮的点击事件
        TextView commit = (TextView)contentView.findViewById(R.id.tv_commit);
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();

            }
        });
    }
}
