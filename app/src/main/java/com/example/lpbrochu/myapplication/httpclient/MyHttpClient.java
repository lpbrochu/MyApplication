package com.example.lpbrochu.myapplication.httpclient;




import org.apache.commons.io.IOUtils;


import java.io.IOException;

import java.net.URL;
import java.net.URLConnection;



/**
 * Created by lpbrochu-u on 1/7/2016.
 */
public class MyHttpClient {




    public String get(String url) throws IOException {
        URL myUrl = new URL(url);
        URLConnection urlConnection = myUrl.openConnection();

        return IOUtils.toString(urlConnection.getInputStream());
    }
}