package com.example.lpbrochu.myapplication.webview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebView;

/**
 * Created by lpbrochu-u on 1/4/2016.
 */
public class MyWebView extends WebView {

    public MyWebView(Context context) {
        super(context);
    }

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void postUrl(String url, byte[] postData) {
        System.out.println("Data: " + postData);
        Log.i("Blabla", "Data: " + postData);
        super.postUrl(url, postData);
    }

    @Override
    public void loadUrl(String url) {
        Log.i("Blabla", "loading url: " + url);
        super.loadUrl(url);
    }


}
