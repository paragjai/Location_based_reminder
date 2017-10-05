package com.example.paragjain.grid_view_slidenerd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by paragjain on 5/10/17.
 */

public class MyDialog extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydialog);
        Intent intent = getIntent();
        if(intent!=null)
        {
            int imageId = intent.getIntExtra("countryImage", R.drawable.jl1);
            String countryName = intent.getStringExtra("countryName"); /*countryName ka content is defined in strings.xml*/
            ImageView myImage = (ImageView) findViewById(R.id.myDialogImage);
            myImage.setImageResource(imageId);
            TextView myText = (TextView) findViewById(R.id.myDialogText);
            myText.setText("This photo belongs to "+countryName);
        }
    }

    public void closeDialog(View v){
        finish();
    }

}
