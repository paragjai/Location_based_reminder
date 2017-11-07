package com.example.paragjain.firebaseauthentication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URL;
import java.util.HashMap;

public class login extends Activity {

    public static final String TAG = login.class.getSimpleName();
    private EditText email, password, name;
    private Button login, signup;
    private DbHelper db;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Log.d(TAG, "login activity.");
        session = new Session(this);
        db = new DbHelper(this);
        email = (EditText) findViewById(R.id.etEmail);
        password = (EditText) findViewById(R.id.etPassword);
        name = (EditText) findViewById(R.id.etName);
        login = (Button) findViewById(R.id.bLogin);
        signup = (Button) findViewById(R.id.bSignUp);

        if(session.loggedin()){
            Intent intent = new Intent(this, listOfList.class);
            startActivity(intent);
            finish();
        }
    }

    public void signUp(View v){
        Log.d(TAG, "sign Up button clicked.");
        Intent intent = new Intent(login.this, SignUp.class);
        finish();
        startActivity(intent);
    }

    public void login(View v){
        String emailContent = email.getText().toString().trim();
        String passwordContent = password.getText().toString().trim();
        String secret = "fb943a2432995dc8114f15f868bbec305fac35b82e610286a2155e807cb577d4";

        HashMap<String, String> arguments = new HashMap<>();
        arguments.put("email", emailContent);
        arguments.put("password", passwordContent);
        arguments.put("url", "http://locationreminder.azurewebsites.net/login");
        arguments.put("secret", secret);

        queryapi q = new queryapi(arguments);
        q.execute();

        if(1==1)//if(db.getUser(getEmail, getPassword))
        {
            session.setLoggedIn(true);
            startActivity(new Intent(login.this, listOfList.class));
            finish();
        }
        else
        {
                Toast.makeText(getApplicationContext(), "Wrong email/password", Toast.LENGTH_SHORT).show();
        }
        // SEND QUERY TO THE DATABASE ( CLOUD ) and check if the user already exists or not
    }


}
