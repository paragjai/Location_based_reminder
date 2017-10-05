package com.example.paragjain.todolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by paragjain on 5/10/17.
 */

public class EditFieldClass extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.to_do_layout);
    }

    public void saveButtonClicked(View v){
        String messageText = ((EditText) findViewById(R.id.message)).getText().toString();
        if(messageText.equals(""))
        {

        }
        else
        {
            Intent intent = new Intent();/*passing the message obtained in this activity back to MainActivity*/
            intent.putExtra(Intent_Constants.INTENT_MESSAGE_FIELD, messageText); /*key will be where the intent will be passed, key must be a string*/
            setResult(Intent_Constants.INTENT_RESULT_CODE, intent);
            finish();
        }
    }

}
