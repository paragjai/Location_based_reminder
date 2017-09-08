package com.example.paragjain.software_engineering;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.android.gms.auth.api.signin.RevocationBoundService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity{


    /*If it cannot resolve RecyclerView and LinearLayoutManager add
    compile 'com.android.support:recyclerview-v7:23.0.0'
    to build.gradle(Module:app)
     */
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerAdapter mRecyclerAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        /*this is a reference variable that refers to the current object*/
        /*Here we are setting LinearLayout for the RecyclerView and not for the row*/
        mLinearLayoutManager = new LinearLayoutManager(this);
        /* Setting a Linear Layout for the Recycler View. We can choose one of three layouts :
        i) Linear Layout.
        ii) Grid Layout.
        iii) Staggered Grid Layout
         */
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        /*Input data for the List is passed to the Adapter's ctor*/
        List<String> input = new ArrayList<>();

        int i = 0;
        for(i=0;i<100;++i)
        {
            input.add("Test" + i);
        }

        mRecyclerAdapter = new RecyclerAdapter(input);

        /*set an adapter to the inflated recycler view layout using setAdapter method*/
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }
}

