package com.example.a1605272.jsondemo;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DownloadTask task= new DownloadTask();
        task.execute("https://openweathermap.org/");

    }



    @SuppressLint("StaticFieldLeak")
    public class DownloadTask extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... urls) {
            StringBuilder result= new StringBuilder();
            URL url;
            HttpURLConnection urlConnection=null;
            try {


                url= new URL(urls[0]);

                urlConnection= (HttpURLConnection)url.openConnection();

                urlConnection.connect();

                InputStream in =urlConnection.getInputStream();

                InputStreamReader reader=new InputStreamReader(in);

                int data = reader.read();
                while(data !=-1)
                {
                    char current=(char)data;
                    result.append(current);
                    data=reader.read();
                }

                return result.toString();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result != null)
                Log.i("website content",result);
            else
                Log.e("website content","error");
        }
    }
}
