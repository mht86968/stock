package com.mht.stock.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mht.stock.R;
import com.mht.stock.activity.base.BaseActivity;
import com.mht.stock.constant.Constants;
import com.mht.stock.databinding.ActivityWebBinding;
import com.mht.stock.util.IO;
import com.mht.stock.util.Utils;
import com.mht.stock.util.ViewUtils;

import java.io.IOException;

public class WebActivity extends BaseActivity {

    private ActivityWebBinding mViewDataBinding;

    private String mUrl;
    private String mTitle;

    public static void toActivity(Context context, Intent intent, String url, String title) {
        intent.setClass(context, WebActivity.class);
        intent.putExtra(Constants.PARAM_URL, url);
        intent.putExtra(Constants.PARAM_TITLE, title);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_web);

        mUrl = getIntent().getStringExtra(Constants.PARAM_URL);
        mTitle = getIntent().getStringExtra(Constants.PARAM_TITLE);

        mViewDataBinding.toolbar.setTitle(mTitle);
        setSupportActionBar(mViewDataBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
    }

    private void init() {
        WebSettings ws = mViewDataBinding.webView.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setSupportZoom(true);
        ws.setBuiltInZoomControls(true);    // 设置支持缩放
        ws.setUseWideViewPort(true);        // 设置预览模式
        if(Utils.hasHoneycomb()) {
            //webview 远程代码执行（JavascriptInterface）
            mViewDataBinding.webView.removeJavascriptInterface("accessibility");
            mViewDataBinding.webView.removeJavascriptInterface("accessibilityTraversal");
            mViewDataBinding.webView.removeJavascriptInterface("searchBoxJavaBridge_");
        }
        ViewUtils.setZoomControlGone(mViewDataBinding.webView);
        mViewDataBinding.webView.setInitialScale(100);
        mViewDataBinding.webView.setWebChromeClient(new WebChromeClient());
        mViewDataBinding.webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                setStockNum();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        mViewDataBinding.webView.loadUrl(mUrl);
    }

    private void setStockNum() {
        try {
            mViewDataBinding.webView.loadUrl(IO.readAsString(getAssets().open("tonghuashun.js")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        if(mViewDataBinding.webView.canGoBack()) {
            mViewDataBinding.webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewDataBinding.webView.destroy();
    }

    public static void startActivity(Context context, String url, String title) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(Constants.PARAM_URL, url);
        intent.putExtra(Constants.PARAM_TITLE, title);
        context.startActivity(intent);
    }
}
