package com.example.paragjain.recycler_view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by paragjain on 27/8/17.
 */

public class Reminder_List_Adapter extends RecyclerView.Adapter<Reminder_List_Adapter.ViewHolder> {

    private List<String> description_;
    private List<String> location_;
    public Reminder_List_Adapter(List<String> description, List<String> location)
    {
        description_ = description;
        location_ = location;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView vhCalendar_;
        public TextView vhDescription_; /*Keep these public, you need to populate it with data in onBindViewHolder*/
        public TextView vhLocation_;
        public View vhRowLayout_;

        public ViewHolder(View v)
        {
            super(v);
            vhRowLayout_ = v;
            vhDescription_= (TextView) v.findViewById(R.id.tvDescription);
            vhLocation_ = (TextView) v.findViewById(R.id.tvLocation);
        }

    }

    @Override
    public Reminder_List_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.item_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(Reminder_List_Adapter.ViewHolder holder, int position) {

        final String description_for_item_at_position = description_.get(position);
        final String location_for_item_at_position = location_.get(position);
        holder.vhDescription_.setText(description_for_item_at_position);
        holder.vhLocation_.setText(location_for_item_at_position);

    }

    @Override
    public int getItemCount() {
        /*size is the method in List<String> class. Returns number of elements in the list.*/
        return description_.size();
    }
}
