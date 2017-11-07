package com.example.paragjain.volley;

import android.widget.TextView;

/**
 * Created by paragjain on 14/10/17.
 */

public class Single_Item {

    private String friendEmail;

    public Single_Item(String friendEmail)
    {
        this.friendEmail = friendEmail;
    }

    public String getFriendEmail()
    {
        return friendEmail;
    }
}
