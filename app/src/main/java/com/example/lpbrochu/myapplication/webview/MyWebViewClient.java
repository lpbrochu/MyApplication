package com.example.lpbrochu.myapplication.webview;

import android.util.Log;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by lpbrochu-u on 1/4/2016.
 */


public class MyWebViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Log.i("Blabla", "url: " + url);
        return super.shouldOverrideUrlLoading(view, url);
    }

    @Override
    public void onReceivedLoginRequest(WebView view, String realm, String account, String args) {
        Log.i("Blabla", "login: ");
        super.onReceivedLoginRequest(view, realm, account, args);
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
        Log.i("Blabla", "url: " + url);
        return super.shouldInterceptRequest(view, url);
    }
}
