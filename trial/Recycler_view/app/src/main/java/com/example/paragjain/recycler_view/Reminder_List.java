package com.example.paragjain.recycler_view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Reminder_List extends Activity {

    /* add     compile 'com.android.support:recyclerview-v7:+'
        to
                build.gradle(Module:app)
    */
    private RecyclerView recyclerView_;
    private RecyclerView.LayoutManager layoutManager_;
    private Reminder_List_Adapter reminder_list_adapter_;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*My layout file is list_layout.xml*/
        setContentView(R.layout.list_layout);

        /*I know my xml file, let me find the views now*/
        recyclerView_ = (RecyclerView) findViewById(R.id.rlvRecycleListView);

        /*Who decides when to recycle the item view????
        LAYOUT MANAGER..:):)
        */

        layoutManager_ = new LinearLayoutManager(this);

        recyclerView_.setLayoutManager(layoutManager_);


        /* CHANGE THIS PART ACCORDING TO THE REQUIREMENT */

        List<String> description = new ArrayList<>();
        List<String> location = new ArrayList<>();
        int i = 0;
        for(i = 0; i < 100; ++i)
        {
            description.add("description:"+i);
            location.add("location:"+i);
        }

        reminder_list_adapter_ = new Reminder_List_Adapter(description,location);
        recyclerView_.setAdapter(reminder_list_adapter_);

    }
}
