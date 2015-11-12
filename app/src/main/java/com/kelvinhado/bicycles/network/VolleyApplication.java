package com.kelvinhado.bicycles.network;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by kel on 10/16/15.
 */

//TODO Add this line to the manifest : android:name=".http.VolleyApplication" in application node
public class VolleyApplication extends Application {

    private static RequestQueue requestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        requestQueue = Volley.newRequestQueue(this);
    }

    public static RequestQueue getRequestQueue() {
        return requestQueue;
    }
}
