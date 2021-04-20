package com.example.nation_info;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import javax.net.ssl.HttpsURLConnection;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {
    private Button btnSubmit;
    Activity activity;
    ArrayList<Country> countries;
    ListView listView;
    CustomCountryList customCountryList;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        btnSubmit = findViewById(R.id.btnSubmit);
        listView = (ListView) findViewById(R.id.list1);
        countries=new ArrayList<>();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countries.clear();
                new GetServerData().execute("http://api.geonames.org/countryInfoJSON?username=huyen_922");
            }
        });
    }
    private class GetServerData extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder data = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    data.append(line);
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data.toString();
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
           try {
                JSONObject object = new JSONObject(s);
                JSONArray array = object.getJSONArray("geonames");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject country = array.getJSONObject(i);
                    int id = country.getInt("geonameId");
                    String countryName = country.getString("countryName");
                    int population = country.getInt("population");
                    double area = country.getDouble("areaInSqKm");
                    String code=country.getString("countryCode");
                    Country countryObj = new Country(id, countryName,code, population, area);
                    countries.add(countryObj);
                }

               customCountryList = new CustomCountryList(MainActivity.this, countries);
               listView.setAdapter(customCountryList);
               listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                   @Override
                   public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                       LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                       View popupView = inflater.inflate(R.layout.popup_window, null);
                       final PopupWindow popupWindow = new PopupWindow(popupView, 800, 1000,true);

                       //set content popup
                       TextView textViewId=(TextView)popupView.findViewById(R.id.textViewId);
                       TextView textViewName = (TextView) popupView.findViewById(R.id.textViewCountryName);
                       TextView textViewPopulation=(TextView) popupView.findViewById(R.id.textViewPopulation);
                       TextView textViewArea=(TextView) popupView.findViewById(R.id.textViewArea);
                       ImageView flag=(ImageView) popupView.findViewById(R.id.imgViewFlag);

                       textViewId.setText("Id: "+countries.get(position).getId());
                       textViewName.setText("Name: "+countries.get(position).getCountryName());
                       textViewPopulation.setText("Population: "+countries.get(position).getPopulation());
                       textViewArea.setText("Area in sqKm: "+countries.get(position).getArea());
                       String url="https://img.geonames.org/flags/x/"+countries.get(position).getCode().toLowerCase()+".gif";
                       Picasso.with(MainActivity.this).load(url).into(flag);
                       popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                       // dismiss the popup window when touched
                       popupView.setOnTouchListener(new View.OnTouchListener() {
                           @Override
                           public boolean onTouch(View v, MotionEvent event) {
                               popupWindow.dismiss();
                               return true;
                           }
                       });
                   }
               });
            } catch (JSONException e) {
               e.printStackTrace();
           }
        }


    }
}