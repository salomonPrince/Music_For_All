package com.example.music_for_all;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherFragment extends Fragment {

    EditText editText;
    TextView textView;
    Button button;

    public WeatherFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflates the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        editText = view.findViewById(R.id.locationedittext);
        textView = view.findViewById(R.id.txt);
        button = view.findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String location = editText.getText().toString();
                if (!location.isEmpty()){
                    new FetchWeatherTask().execute(location);
                } else {
                    Toast.makeText(getContext(), "Please provide a location", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private class FetchWeatherTask extends AsyncTask<String ,Void,String> {

        @Override
        protected String doInBackground(String... params) {
            String apiKey = "402141799aba350e3b1460f9db667ba7";
            String location = params[0];
            String apiURL = "https://api.openweathermap.org/data/2.5/weather?q=" + location
                    +"&appid=" + apiKey;
            try {
                URL url = new URL(apiURL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line).append("  ");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result != null){
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONObject mainObject = jsonObject.getJSONObject("main");
                    double temp = mainObject.getDouble("temp");
                    JSONArray weatherArray = jsonObject.getJSONArray("weather");

                    JSONObject weatherObject = weatherArray.getJSONObject(0);
                    String weatherDesc = weatherObject.getString("description");

                    String weatherInfo = "Weather: " + weatherDesc + ", Temperature: " + temp + "°C";
                    textView.setText(weatherInfo);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
