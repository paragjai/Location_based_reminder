package com.example.paragjain.todolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    ListView listView;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    String messageText;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        arrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, EditMessageClass.class);
                    intent.putExtra(Intent_Constants.INTENT_MESSAGE_DATA, arrayList.get(position).toString()); /*passing data to EditMessageClass activity using putExtra*/
                    intent.putExtra(Intent_Constants.INTENT_ITEM_POSITION, position);
                    startActivityForResult(intent, Intent_Constants.INTENT_REQUEST_CODE_TWO);


            }

        });
    }

    public void onClick(View v){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, EditFieldClass.class);
        /*When EditFieldClass completely gets executed, it'll call onActivityResult method. EditFieldClass can be uniquely identified using INTENT_REQUEST_CODE as the resultCode*/
        startActivityForResult(intent, Intent_Constants.INTENT_REQUEST_CODE);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode==Intent_Constants.INTENT_REQUEST_CODE)
        {   /*true when EditFieldClass Activity has finished execution and has responded*/
            messageText = data.getStringExtra(Intent_Constants.INTENT_MESSAGE_FIELD); /*message obtained from EditFieldClass Activity*/
            arrayList.add(messageText);
            arrayAdapter.notifyDataSetChanged();

        }
        else if(resultCode==Intent_Constants.INTENT_REQUEST_CODE_TWO){
            messageText = data.getStringExtra(Intent_Constants.INTENT_CHANGED_MESSAGE); /*message obtained from EditFieldClass Activity*/
            position = data.getIntExtra(Intent_Constants.INTENT_ITEM_POSITION, -1);
            arrayList.remove(position);
            arrayList.add(position,messageText);
            arrayAdapter.notifyDataSetChanged();
        }

    }


}
