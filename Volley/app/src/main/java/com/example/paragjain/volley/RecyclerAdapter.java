package com.example.paragjain.volley;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by paragjain on 14/10/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter {

    ArrayList<Single_Item> single_item_list = new ArrayList<>();

    public RecyclerAdapter(ArrayList<Single_Item> arrayList)
    {
        Log.d("RecyclerAdapter: ", "reached here");
        this.single_item_list = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        Log.d("onCreateViewHolder: ", "inflated single_item.xml");
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            String dest_email = single_item_list.get(position).getFriendEmail();
            ((MyViewHolder) holder).friendEmail.setText(dest_email);
            Log.d("onBindViewHolder: ", dest_email);
    }


    @Override
    public int getItemCount() {
        Log.d("getItemCount: ", Integer.toString(single_item_list.size()));
        return single_item_list.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView friendEmail;
        public MyViewHolder(View singleItemView) {
            super(singleItemView);
            Log.d("MyViewHolder called", "MyViewHolder called");
            friendEmail = (TextView) singleItemView.findViewById(R.id.tvFriendEmail);
        }
    }
}
