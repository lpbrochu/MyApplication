package com.example.lpbrochu.myapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.CookieManager;
import android.webkit.WebSettings;

import com.example.lpbrochu.myapplication.httpclient.MyHttpClient;
import com.example.lpbrochu.myapplication.httpclient.WebkitCookieManagerProxy;
import com.example.lpbrochu.myapplication.webview.MyWebView;
import com.example.lpbrochu.myapplication.webview.MyWebViewClient;

import java.io.IOException;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                new AsyncTask<Void, Void, Void>(){
                    @Override
                    protected Void doInBackground(Void... params) {
                        MyHttpClient httpClient = new MyHttpClient();
                        String newReleaseJson = null;
                        try {
                            newReleaseJson = httpClient.post("https://play.pocketcasts.com/web/episodes/new_releases_episodes.json");
                            Snackbar.make(view, "New release: " + newReleaseJson, Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                }.execute();

            }
        });

        MyWebView myWebView = (MyWebView) findViewById(R.id.webView);
        myWebView.setWebViewClient(new MyWebViewClient());
        WebSettings webSettings = myWebView.getSettings();
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        webSettings.setJavaScriptEnabled(true);

        initCookies(getApplicationContext());

        myWebView.loadUrl("https://play.pocketcasts.com/users/sign_in");
    }

    private void initCookies(Context appContext) {
        android.webkit.CookieSyncManager.createInstance(appContext);
        // unrelated, just make sure cookies are generally allowed
        android.webkit.CookieManager.getInstance().setAcceptCookie(true);

        // magic starts here
        WebkitCookieManagerProxy coreCookieManager = new WebkitCookieManagerProxy(null, java.net.CookiePolicy.ACCEPT_ALL);
        java.net.CookieHandler.setDefault(coreCookieManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
