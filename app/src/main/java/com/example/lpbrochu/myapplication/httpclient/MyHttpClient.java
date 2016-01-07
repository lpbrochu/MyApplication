package com.example.lpbrochu.myapplication.httpclient;




import android.util.Log;
import android.webkit.CookieManager;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;


import java.io.BufferedOutputStream;
import java.io.IOException;

import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;



/**
 * Created by lpbrochu-u on 1/7/2016.
 */
public class MyHttpClient {

    private HttpClient httpClient;

    public MyHttpClient(CookieManager cookieManager){
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setDefaultCookieStore(createCookieStore(cookieManager));
        httpClient = httpClientBuilder.build();
    }

    private CookieStore createCookieStore(CookieManager cookieManager) {
        String url = "play.pocketcasts.com";
        String cookies = cookieManager.getCookie(url);
        Log.d(MyHttpClient.class.toString(), "All the cookies in a string:" + cookies);
        String[] cookieValues = cookies.split(";");
        BasicCookieStore cs = new BasicCookieStore();

        BasicClientCookie cookie;
        for (int i = 0; i < cookieValues.length; i++) {
            String[] split = cookieValues[i].split("=");
            if (split.length == 2)
                cookie = new BasicClientCookie(split[0], split[1]);
            else
                cookie = new BasicClientCookie(split[0], null);

            cookie.setDomain(url);
            cs.addCookie(cookie);
        }
        return cs;
    }

    public String get(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        HttpResponse httpResponse = httpClient.execute(httpGet);

        Log.d(this.getClass().toString(), "httpResponse=" + httpResponse.getStatusLine());
        return IOUtils.toString(httpResponse.getEntity().getContent());
    }

    public String post(String url) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        HttpResponse httpResponse = httpClient.execute(httpPost);

        Log.d(this.getClass().toString(), "httpResponse=" + httpResponse.getStatusLine());
        return IOUtils.toString(httpResponse.getEntity().getContent());
    }

    private void writeStream(OutputStream out) throws IOException {
        out.write("TOTO".getBytes());
    }
}