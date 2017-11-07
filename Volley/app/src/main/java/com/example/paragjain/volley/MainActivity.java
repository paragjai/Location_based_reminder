package com.example.paragjain.volley;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity{

    TextView name;
    TextView email;
    TextView phoneNumber;
    Button json_object;
    Button json_array;
    String json_object_url = "http://192.168.43.196/LocationBasedReminder/getInfo.php";
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.json_object);
        //setContentView(R.layout.json_array);
        name = (TextView) findViewById(R.id.tvName);
        email = (TextView) findViewById(R.id.tvEmail);
        phoneNumber = (TextView) findViewById(R.id.tvPhoneNumber);
        json_object = (Button) findViewById(R.id.bGetResponse);
        json_array = (Button) findViewById(R.id.bGetJsonArrayResponse);
    }

    void getJsonObjectResponse(View v){

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        /*StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {
                textView.setText(response);
                requestQueue.stop();
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("Something went wrong.");
                error.printStackTrace();
                requestQueue.stop();
            }
        } );*/

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, json_object_url, (String) null,
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                Log.d("Response received: ", "MainActivity.this");
                                name.setText(response.getString("userName"));
                                email.setText(response.getString("email"));
                                phoneNumber.setText(response.getString("phoneNumber"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener()

                        {

                            @Override
                            public void onErrorResponse (VolleyError error){
                            Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                            error.printStackTrace();
                            requestQueue.stop();
                            }
                        }

                );


        MySingletonJsonObject.getInstance(MainActivity.this).addToRequestQueue(jsonObjectRequest);


}

    void getJsonArrayResponse(View v){
        Log.d("JSON array clicked: ", "In MainActivity.this => going to DisplayList.class");
        Intent intent = new Intent(MainActivity.this, DisplayList.class);
        startActivity(intent);
    }
}
