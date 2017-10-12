package com.example.paragjain.firebaseauthentication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by paragjain on 11/10/17.
 */

public class DbHelper extends SQLiteOpenHelper {

    public static final String TAG = DbHelper.class.getSimpleName();
    public static final String DB_NAME = "myapp.db";
    public static final int DB_VERSION = 1;

    public static final String USER_TABLE = "users";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASS = "password";
    public static final String COLUMN_USERNAME = "userName";
    public static final String COLUMN_PHONENUMBER = "phoneNumber";

    public static final String CREATE_TABLE_USERS = "CREATE TABLE " + USER_TABLE + "(" + COLUMN_EMAIL + " TEXT PRIMARY KEY , "
            + COLUMN_USERNAME + " TEXT NOT NULL, " + COLUMN_PASS + " TEXT NOT NULL, " + COLUMN_PHONENUMBER + " TEXT NOT NULL);";

    public DbHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE_USERS);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        onCreate(db);
    }

    public boolean addUser(String email, String password, String userName, String phoneNumber){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASS, password);
        values.put(COLUMN_USERNAME, userName);
        values.put(COLUMN_PHONENUMBER, phoneNumber);

        if(!verify(email)) {
            long id = db.insert(USER_TABLE, null, values);
            Log.d(TAG, "user inserted: " + id + " " + email);
            return true;
        }
        else
        {
            Log.d(TAG, "user already exists: " + email);
            return false;

        }
    }

    public boolean verify(String email){
        String selectQuery = "select * from " + USER_TABLE + " where " + COLUMN_EMAIL + " = " + "'" + email + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        if(cursor.getCount()>0)
        {
            return true;
        }
        else
            return false;

    }

    public boolean getUser(String email, String password){
        String selectQuery = "select * from " + USER_TABLE + " where " + COLUMN_EMAIL + " = " + "'"+ email + "' and " + COLUMN_PASS + " = " + "'" + password + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount()>0){
            Log.d("count: " , Integer.toString(cursor.getCount()));
                do {

                    for ( int i = 0; i < cursor.getColumnCount() ; ++i)
                    {
                        Log.e("", "" + cursor.getString(i));
                    }
                }while(cursor.moveToNext());

            return true;
        }
        cursor.close();
        db.close();
        return false;

    }
}
