package com.example.paragjain.todolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by paragjain on 5/10/17.
 */

public class EditMessageClass extends Activity {

    String messageText;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.to_do_layout);
        Intent intent =    getIntent();
        messageText = intent.getStringExtra(Intent_Constants.INTENT_MESSAGE_DATA);
        position = intent.getIntExtra(Intent_Constants.INTENT_ITEM_POSITION, -1);
        EditText messageData = (EditText) findViewById(R.id.message);
        messageData.setText(messageText);
    }

    public void saveButtonClicked(View v){
        String changedMessageText = ((EditText) findViewById(R.id.message)).getText().toString();
        Intent intent = new Intent();/*passing the message obtained in this activity back to MainActivity*/
        intent.putExtra(Intent_Constants.INTENT_CHANGED_MESSAGE, changedMessageText); /*key will be where the intent will be passed, key must be a string*/
        intent.putExtra(Intent_Constants.INTENT_ITEM_POSITION, position);
        setResult(Intent_Constants.INTENT_RESULT_CODE_TWO, intent); /*wii help identify this activity's response in the parent activity*/

        finish();

    }

}
