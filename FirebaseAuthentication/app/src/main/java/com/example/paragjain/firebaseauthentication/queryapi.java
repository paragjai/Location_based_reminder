package com.example.paragjain.firebaseauthentication;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.*;
import java.net.URL;

/**
 * Created by Nikhil Prabhu on 11/7/2017.
 */

public class queryapi extends AsyncTask<URL, Integer, String> {
    String urlString = "";
    //String email;
    //String  name;
    //String phoneno;
    //String password;
    //String secret;
    HttpURLConnection urlConnection = null;
    URL url = null;
    String data = "";
    InputStream stream = null;
    HashMap<String, String> hm = null;

    public queryapi(HashMap<String,String> hm)
    {
        this.hm = hm;
    }

    protected String doInBackground(URL... urls) {


        URL url = null;
        InputStream stream = null;
        HttpURLConnection urlConnection = null;
        try {
            urlString = hm.get("url");
            url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            for(Map.Entry<String, String> e:hm.entrySet())
            {
                if (!(e.getKey().equals("url"))){
                    data += URLEncoder.encode(e.getKey(), "UTF-8") + "=" + URLEncoder.encode(e.getValue(), "UTF-8") + "&";
                }
            }
            //StringBuilder sb = new StringBuilder(data);
            data = data.substring(0, data.length() - 1);

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
       /*protected void onProgressUpdate(Integer... progress) {
           setProgressPercent(progress[0]);
       }*/

    // This is called when doInBackground() is finished
    protected void onPostExecute(String result) {
        //Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
        Log.w("query msg rc:",result);
    }
}
