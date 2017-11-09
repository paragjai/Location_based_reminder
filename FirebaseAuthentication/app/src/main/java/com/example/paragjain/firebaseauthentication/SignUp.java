package com.example.paragjain.firebaseauthentication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

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
        final String nameContent = name.getText().toString().trim();
        final String emailContent = email.getText().toString().trim();
        final String phoneNumberContent = phoneNumber.getText().toString().trim();
        final String passwordContent = password.getText().toString().trim();
        final String secret = "fb943a2432995dc8114f15f868bbec305fac35b82e610286a2155e807cb577d4";

        //UPDATE THE DATABASE ( CLOUD ) HERE THROUGH API CALL. ADD HIM TO THE USERS TABLE
        if(emailContent.isEmpty() && nameContent.isEmpty() && phoneNumberContent.isEmpty() && passwordContent.isEmpty()){
            Toast.makeText(SignUp.this, "All fields must be filled. Field empty", Toast.LENGTH_SHORT).show();
        }
        else
        {
            HashMap<String, String> arguments = new HashMap<>();
            arguments.put("name", nameContent);
            arguments.put("email", emailContent);
            arguments.put("phoneno", phoneNumberContent);
            arguments.put("password", passwordContent);
            arguments.put("secret", secret);
            arguments.put("url", "http://locationreminder.azurewebsites.net/signup");
            queryapi q = new queryapi(arguments);
            q.execute();

            /*
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
            */

            /*
            class sendQueryTask extends AsyncTask<URL, Integer, String> {
                // Do the long-running work in here
                protected String doInBackground(URL... urls) {

                    String urlString = "http://locationreminder.azurewebsites.net/signup";
                    String email = emailContent;//"kjasndkjsndkjasndkjasndllknnd";
                    String  name  = nameContent;//"something";
                    String phoneno = phoneNumberContent;//"9739862022";
                    String password = passwordContent;//"something";
                    //String secret = "dnjsak";

                    URL url = null;
                    InputStream stream = null;
                    HttpURLConnection urlConnection = null;
                    try {
                        url = new URL(urlString);
                        urlConnection = (HttpURLConnection) url.openConnection();
                        urlConnection.setRequestMethod("POST");
                        urlConnection.setDoOutput(true);

                        String data = URLEncoder.encode("email", "UTF-8")
                                + "=" + URLEncoder.encode(email, "UTF-8");

                        data += "&" + URLEncoder.encode("name", "UTF-8") + "="
                                + URLEncoder.encode(name, "UTF-8");
                        data += "&" + URLEncoder.encode("phoneno", "UTF-8") + "="
                                + URLEncoder.encode(phoneno, "UTF-8");
                        data += "&" + URLEncoder.encode("password", "UTF-8") + "="
                                + URLEncoder.encode(password, "UTF-8");
                        data += "&" + URLEncoder.encode("secret", "UTF-8") + "="
                                + URLEncoder.encode(secret, "UTF-8");

                        urlConnection.connect();

                        OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream());
                        wr.write(data);
                        wr.flush();

                        stream = urlConnection.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"), 8);
                        String result = reader.readLine();

                        return result;

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (urlConnection != null) {
                            urlConnection.disconnect();
                        }
                    }

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Log.i("Result", "SLEEP ERROR");
                    }
                    return null;
                }

                // This is called each time you call publishProgress()
       //*protected void onProgressUpdate(Integer... progress) {
           setProgressPercent(progress[0]);
       //}//

                // This is called when doInBackground() is finished
                protected void onPostExecute(String result) {
                    //Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
                    Log.w("query msg rc:",result);
                }
            }

            */
            //new sendQueryTask().execute();

        }

        SharedPreferences preferences = getSharedPreferences("MeraPreference", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(emailContent+passwordContent+"data", emailContent+"\n"+passwordContent);
        editor.commit();

    }



}