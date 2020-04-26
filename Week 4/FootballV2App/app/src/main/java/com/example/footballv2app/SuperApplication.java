package com.example.footballv2app;

import android.app.Application;
import android.util.Log;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;

public class SuperApplication extends Application {

    private static SuperApplication instance = null;
    private RequestQueue requestQueue;


    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024);
        Network network = new BasicNetwork(new HurlStack());
        requestQueue = new RequestQueue(cache, network);
        requestQueue.start();

    }

    public RequestQueue getRequestQueue() {
        return this.requestQueue;
    }


    public static SuperApplication getInstance() {
        return instance;
    }
}
