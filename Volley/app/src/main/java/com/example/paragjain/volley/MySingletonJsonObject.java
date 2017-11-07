package com.example.paragjain.volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by paragjain on 14/10/17.
 */

public class MySingletonJsonObject {
    private static MySingletonJsonObject mInstance;
    private RequestQueue requestQueue;
    private static Context context;


    private MySingletonJsonObject(Context context)
    {

            this.context = context;
            this.requestQueue = getRequestQueue();

    }

    public RequestQueue getRequestQueue(){

        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());

        }
        return requestQueue;
    }


    public static synchronized MySingletonJsonObject getInstance(Context context)
    {
        if(mInstance == null)
        {
            mInstance = new MySingletonJsonObject(context);
        }
        return mInstance;
    }

    public<T> void addToRequestQueue(Request<T> request)
    {
        requestQueue.add(request);
    }

}
