package com.example.paragjain.volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by paragjain on 14/10/17.
 */

public class MySingletonJsonArrayObject {

    private static MySingletonJsonArrayObject mInstance;
    private RequestQueue requestQueue;
    private static Context context;


    private MySingletonJsonArrayObject(Context context)
    {

        this.context = context;
        this.requestQueue = getRequestQueue();

    }

    public RequestQueue getRequestQueue(){

        if(requestQueue == null){
            /*keep the requestQueue alive as long as the application lifecycle*/
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());

        }
        return requestQueue;
    }


    public static synchronized MySingletonJsonArrayObject getInstance(Context context)
    {
        if(mInstance == null)
        {
            mInstance = new MySingletonJsonArrayObject(context);
        }
        return mInstance;
    }

    public<T> void addToRequestQueue(Request<T> request)
    {
        requestQueue.add(request);
    }

}
