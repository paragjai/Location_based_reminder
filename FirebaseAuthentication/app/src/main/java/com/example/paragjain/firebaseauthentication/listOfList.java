package com.example.paragjain.firebaseauthentication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by paragjain on 11/10/17.
 */

public class listOfList extends Activity {
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.listoflist);


    }
    public void logoutHandler(View view)
    {
        //session.setLoggedIn(false);
        Intent intent = new Intent(getApplicationContext(), login.class);
        intent.putExtra("logMeOut", 1);
        startActivity(intent);
        finish();
    }
}
