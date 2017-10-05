package com.example.paragjain.grid_view_slidenerd;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ContentFrameLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    /*
    1. prepare data sources [ images in drawable and country names in strings.xml ]
    2. create a GridView in activity_main.xml
    3. Bring GridView from xml to Java [ to fill values ]
    */

    GridView myGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myGrid = (GridView) findViewById(R.id.gridView);
        myGrid.setAdapter(new ParagAdapater(this));
        myGrid.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
         /*first parameter represents the GridView (activity_main.xml)
         second parameter represents the Relative Layout which is a root view for single_item.xml
         third parameter is the position where the item was clicked (0, 1 etc.)
          */
         Intent intent = new Intent(this, MyDialog.class);
        ViewHolder holder = (ViewHolder) view.getTag();
        Country temp = (Country) holder.myImageView.getTag();
        intent.putExtra("countryImage", temp.imageId);
        intent.putExtra("countryName", temp.countryName);

        startActivity(intent);


    }
}

class Country
{
    int imageId;
    String countryName;
    boolean checkedValue;

    Country(int imageId, String countryName)
    {
        this.imageId = imageId;
        this.countryName = countryName;
        this.checkedValue = false;
    }
}

class ViewHolder
{
    ImageView myImageView;
    TextView myTextView;
    CheckBox myCheckBox;
    ViewHolder(View v)
    {
        myImageView = (ImageView) v.findViewById(R.id.imageView);
        myTextView = (TextView) v.findViewById(R.id.textView);
        myCheckBox = (CheckBox) v.findViewById(R.id.checkBox);
    }
}


class ParagAdapater extends BaseAdapter{

    ArrayList<Country> list;
    Context context;
    ParagAdapater(Context context)
    {
        this.context = context;
        list = new ArrayList<Country>();
        Resources res = context.getResources();
        String[] tempCountryNames = res.getStringArray(R.array.picture_names);
        int[] countryImages = {R.drawable.jl1,R.drawable.jl2, R.drawable.jl3,R.drawable.jl4, R.drawable.jl5};

        for(int i = 0; i < 5; ++i)
        {
            Country tempCountry = new Country(countryImages[i], tempCountryNames[i]);
            list.add(tempCountry);


        }

    }

    @Override
    public int getCount() {

        /* return size of the ArrayList */
        return list.size();
    }

    @Override
    public Object getItem(int i) {

        /* return a particular element from the ArrayList */
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row = view;
        ViewHolder holder = null;
        if(row==null)
        {
            /*Creating stuff for the first time*/
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_item, viewGroup, false); /*row object contains Relative Layout of single_item.xml*/
            holder = new ViewHolder(row); /*using this row object we are able to access the ImageView of single_item.xml*/
            row.setTag(holder);
        }
        else
        {
            /*we are reusing/recycling stuff*/
            /*whenever we are recycling we are not calling the ctor of ViewHolder, we are not calling the resources using findViewById and thats how the optimization happens*/
             holder = (ViewHolder) row.getTag();
        }
        /*putting the stuff inside the holder*/
        Country temp = list.get(i);
        holder.myImageView.setTag(temp); /*used to get it in onItemClick using getTag*/
        holder.myTextView.setText(temp.countryName);
        holder.myCheckBox.setChecked(temp.checkedValue);
        holder.myImageView.setImageResource(temp.imageId);
        return row;
    }
}