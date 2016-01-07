package com.example.lpbrochu.myapplication.httpclient;




import org.apache.commons.io.IOUtils;


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




    public String get(String url) throws IOException {
        URL myUrl = new URL(url);
        URLConnection urlConnection = myUrl.openConnection();

        return IOUtils.toString(urlConnection.getInputStream());
    }

    public String post(String url) throws IOException {
        URL myUrl = new URL(url);
        URLConnection urlConnection = myUrl.openConnection();
        urlConnection.setDoOutput(true);

        OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
        writeStream(out);

        return IOUtils.toString(urlConnection.getInputStream());
    }

    private void writeStream(OutputStream out) throws IOException {
        out.write("TOTO".getBytes());
    }
}