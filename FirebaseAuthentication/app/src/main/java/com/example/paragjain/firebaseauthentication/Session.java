package com.example.paragjain.firebaseauthentication;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by paragjain on 11/10/17.
 */

public class Session {
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;

    public Session(Context ctx){
        this.ctx = ctx;
        prefs = ctx.getSharedPreferences("myapp", Context.MODE_PRIVATE);
        editor = prefs.edit();

    }

    public void setLoggedIn(boolean loggedIn){
        editor.putBoolean("loggedInMode", loggedIn);
        editor.commit();
    }

    public boolean loggedin(){
        return prefs.getBoolean("loggedInMode", false);
    }
}
