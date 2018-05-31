package com.example.administrator.androidweather;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.androidweather.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    ListView listview;
    ArrayList<Weather> weathers;
    private static String URL_REQUEST = "https://api.openweathermap.org/data/2.5/group?id=2172797,707860,596826,519188,1283378,524901,703448,2643743&units=metric&appid=c30f8c9e48b08a92b897370f80db6523";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        weathers = new ArrayList<>();
        new GetWeatherApi().execute();

    }
    public void init(){
        listview = (ListView)findViewById(R.id.list_weather);
    }
    private class GetWeatherApi extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this,"Loading...",Toast.LENGTH_SHORT).show();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url1 = new URL(URL_REQUEST);
                HttpsURLConnection connection = (HttpsURLConnection) url1.openConnection();
                InputStream inputStream = new BufferedInputStream(connection.getInputStream());

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                try {
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line).append('\n');
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String result = stringBuilder.toString();

                JSONObject jsonObject = new JSONObject(result);
                JSONArray weather = jsonObject.getJSONArray("list");
                for (int i = 0; i < weather.length(); i++) {
                    JSONObject object = weather.getJSONObject(i);
                    JSONObject object1 = object.getJSONObject("main");
                    JSONArray object2 = object.getJSONArray("weather");
                    JSONObject object3 = object2.getJSONObject(0);
                    Weather modelweather = new Weather();
                    modelweather.setName(object.getString("name"));
                    modelweather.setTemp(object1.getDouble("temp"));
                    modelweather.setIcon(object3.getString("icon"));
                    weathers.add(modelweather);
                }

            } catch (Exception e) {
                e.printStackTrace();

            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
                CustomAdapter adapter = new CustomAdapter(MainActivity.this,R.layout.custom_listview,weathers);
                listview.setAdapter(adapter);
        }


    }
}
