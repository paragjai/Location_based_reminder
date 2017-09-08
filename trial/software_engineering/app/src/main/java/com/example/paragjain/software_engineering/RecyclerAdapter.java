package com.example.paragjain.software_engineering;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by paragjain on 19/8/17.
 */

/*PhotoHolder is a user-defined class. It is referred as a ViewHolderClass.
* Adapter is an innerclass to RecyclerView Class.*/
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.PhotoHolder> {

    private ArrayList<ContactsContract.CommonDataKinds.Photo> mPhotos;

    RecyclerAdapter(ArrayList<ContactsContract.CommonDataKinds.Photo> photos)
    {
        mPhotos = photos;
    }

    @Override
    /*RecycleView asks onCreateViewHolder from RecyclerAdapter to make a new one*/
    public RecyclerAdapter.PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /*Explicitly inflating the layout xml file and creating the view object*/
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_row, parent, false);
        return new PhotoHolder(inflatedView); /*Passing the layout file containing the views for a row by inflating it to PhotoHolder*/
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.PhotoHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }



    /* PhotoHolder is our View Holder for the Adapter
    It is a holder.
    In case 2 classes are logically related to each other, we put inside the other.
    Also, non-static inner class have access to members of outer class. Since here inner class object will have access to outer class members extra memory is used per inner class object here.
    Also, static class do not have access to members of outer class. Here we save on the memory a bit.
     */
    public static class PhotoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mItemImage;
        private TextView mItemDate;
        private TextView mItemDescription;
        private ContactsContract.CommonDataKinds.Photo mPhoto;

        private static final String PHOTO_KEY = "PHOTO";


        /*constructor, View object containing the  inflated layout of the row is passed to the constructor.
        * onCreateViewHolder returns an object of ViewHolder per visual entry(row) in the recycler view. onCreateViewHolder is called only when a new view is to be created.*/
        public PhotoHolder(View v)
        {
            super(v);

            mItemImage = (ImageView) v.findViewById(R.id.item_image);
            mItemDate = (TextView) v.findViewById(R.id.item_date);
            mItemDescription = (TextView) = v.findViewById(R.id.item_description);
            v.hasOnClickListeners(this); /*what happens when you click on that particular item/row of the list*/
        }

        public void onClick(View v)
        {
            Log.d("RecyclerView", "CLICK!");
            Context context = itemView.getContext();
            Intent showPhotoIntent = new Intent(context, PhotoActivity.class);
            showPhotoIntent.putExtra(PHOTO_KEY, mPhoto);
            context.startActivity(showPhotoIntent);
        }

        /*For binding data to the holder*/
        public void bindPhoto(ContactsContract.CommonDataKinds.Photo photo)
        {
            mPhoto = photo;
            Picasso.with(mItemImage.getContext()).load(photo.getUrl()).into(mItemImage);


        }

    }
}

