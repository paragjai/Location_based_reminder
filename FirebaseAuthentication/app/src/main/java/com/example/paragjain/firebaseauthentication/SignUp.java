package com.example.paragjain.firebaseauthentication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by paragjain on 11/10/17.
 */

public class SignUp extends Activity {


    private EditText name;
    private EditText email;
    private EditText phoneNumber;
    private EditText password;
    private Button signUp;
    private DbHelper db;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        db = new DbHelper(this);
        name = (EditText) findViewById(R.id.etName);
        email = (EditText) findViewById(R.id.etEmail);
        phoneNumber = (EditText) findViewById(R.id.etPhone);
        password = (EditText) findViewById(R.id.etPassword);
        signUp = (Button) findViewById(R.id.bSignUp);
    }

    public void signUp(View v){
        String nameContent = name.getText().toString().trim();
        String emailContent = email.getText().toString().trim();
        String phoneNumberContent = phoneNumber.getText().toString().trim();
        String passwordContent = password.getText().toString().trim();

        //UPDATE THE DATABASE ( CLOUD ) HERE THROUGH API CALL. ADD HIM TO THE USERS TABLE
        if(emailContent.isEmpty() && nameContent.isEmpty() && phoneNumberContent.isEmpty() && passwordContent.isEmpty()){
            Toast.makeText(SignUp.this, "All fields must be filled. Field empty", Toast.LENGTH_SHORT).show();
        }
        else
        {
            boolean added = db.addUser(emailContent, nameContent, passwordContent, phoneNumberContent);
            if(added){
                Toast.makeText(SignUp.this, "User registered", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(SignUp.this, "User already exists", Toast.LENGTH_SHORT).show();
            }
            Intent intent = new Intent(SignUp.this, login.class);
            finish();
            startActivity(intent);


        }

        SharedPreferences preferences = getSharedPreferences("MeraPreference", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(emailContent+passwordContent+"data", emailContent+"\n"+passwordContent);
        editor.commit();



    }



}