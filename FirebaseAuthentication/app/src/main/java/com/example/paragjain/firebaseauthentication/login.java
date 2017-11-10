package com.example.paragjain.firebaseauthentication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.HashMap;

public class login extends Activity {

    public static final String TAG = login.class.getSimpleName();
    private EditText email, password, name;
    private Button login, signup;
    private DbHelper db;
    private Session session;
    private static int status = 0;
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

        status = getIntent().getIntExtra("logMeOut", 0);
        Log.e("login Oops0:","" +status);
        if(session.loggedin() && status==0){
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
        try
        {
            String res= q.execute().get();
            Log.w("check: ","val:"+res);

            JSONObject resultJSON = new JSONObject(res);
            int status = resultJSON.getInt("status");
            Log.w("status code result : ","val:"+ status);
            if(status==200)//if(db.getUser(getEmail, getPassword))
            {
                session.setLoggedIn(true);
                Intent it = new Intent(login.this, listOfList.class);

                startActivity(it);

                finish();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Wrong email/password", Toast.LENGTH_SHORT).show();
            }
        }
        catch(JSONException e)
        {
            Log.w("catch block: ","");
            e.printStackTrace();
        }
        catch(Exception e)
        {
            Log.w("catch exception block: ","");
            e.printStackTrace();
        }


        // SEND QUERY TO THE DATABASE ( CLOUD ) and check if the user already exists or not
    }


}
