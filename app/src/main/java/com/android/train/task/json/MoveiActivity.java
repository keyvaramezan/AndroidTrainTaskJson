package com.android.train.task.json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MoveiActivity extends AppCompatActivity {
    private static final String TAG = "MoveiActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final ArrayList<String> movies = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movei);
        String address = "https://www.omdbapi.com/?s=rambo&apikey=70ad462a";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(address, new JsonHttpResponseHandler(){

       @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    JSONObject object = new JSONObject(response.toString());
                    JSONArray array = new JSONArray(object.getString("Search"));
                    for (int i = 0; i < array.length(); i++)
                    {
                        JSONObject movieObject = array.getJSONObject(i);
                        movies.add(movieObject.getString("Title"));
                    }

                    Log.d(TAG, "run: " + movies.get(1));
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });

    }
}
