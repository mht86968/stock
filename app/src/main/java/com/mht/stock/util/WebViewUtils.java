package com.mht.stock.util;

import android.webkit.WebView;

/**
 * Created by mht on 2016/5/6.
 */
public class WebViewUtils {
    public static final String JS_getMeta = "javascript:window.nativeObj.getMeta('%1$s', document.getElementsByName('%1$s')[0].content)";
    public static final String JS_getHtml = "javascript:window.nativeObj.getMeta('Html', document.head.innerHTML)";

    public static void getMeta(WebView webView, String key) {
        String js = String.format(JS_getMeta, key);
        webView.loadUrl(js);
    }

    public static void getHtml(WebView webView) {
        String js = JS_getHtml;
        webView.loadUrl(js);
    }
}
