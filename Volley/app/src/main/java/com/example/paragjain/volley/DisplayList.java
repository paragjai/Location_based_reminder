package com.example.paragjain.volley;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by paragjain on 14/10/17.
 */

public class DisplayList extends Activity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Single_Item> single_item_list = new ArrayList<Single_Item>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.json_array);
        Log.d("Display List: onCreate", "json_array layout set");
        recyclerView = (RecyclerView) findViewById(R.id.rvList);
        //  recyclerView.setHasFixedSize(true);
        BackgroundTask backgroundTask = new BackgroundTask(DisplayList.this);
        backgroundTask.getList();
        //adapter = new RecyclerAdapter(single_item_list);
        //recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }


    public class BackgroundTask {

        Context context;
        ArrayList<Single_Item> arrayList = new ArrayList<>();
        String json_array_url = "http://192.168.43.196/LocationBasedReminder/jsonArray.php";

        //String json_array_url = "http://locationreminder.azurewebsites.net/register";

        public BackgroundTask(Context context) {
            this.context = context;
        }

        public void getList() {
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, json_array_url,
                    (String) null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            int count = 0;
                            while (count < response.length()) {
                                try {
                                    JSONObject jsonObject = response.getJSONObject(count);
                                    Single_Item single_item = new Single_Item(jsonObject.getString("dest_email"));
                                    Log.d("BackgroundTask: ", jsonObject.getString("dest_email"));
                                    arrayList.add(single_item);
                                    ++count;

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            adapter = new RecyclerAdapter(arrayList);
                            recyclerView.setAdapter(adapter);
                            Log.d("onResponse: ", "notified Data changed");

                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                    error.printStackTrace();
                }
            });

            MySingletonJsonArrayObject.getInstance(context).addToRequestQueue(jsonArrayRequest);
            Log.d("getList(): ", Integer.toString(arrayList.size()));


        }
    }
}
