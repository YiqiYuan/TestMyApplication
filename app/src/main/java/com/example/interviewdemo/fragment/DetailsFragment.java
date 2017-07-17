package com.example.interviewdemo.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.interviewdemo.R;
import com.example.interviewdemo.view.PagingChildWebView;

/**
 * Created by Administrator on 2016/4/1.
 */
public class DetailsFragment extends BaseFragment {

    private PagingChildWebView webview;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_details,container,false);
        webview = (PagingChildWebView) view.findViewById(R.id.details_webview);
        return view;
    }

    @Override
    protected void initData() {
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webview.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        setData();

    }

    @Override
    protected void initListener() {

    }

    public void setData(){
        webview.loadUrl("http://news.qq.com/");

    }
}
