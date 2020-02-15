package com.android.train.task.json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.train.task.json.imdb.MovieList;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class JsonToRecylcerActivity extends AppCompatActivity {

    private static final String TAG = "MoveiActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final ArrayList<String> movies = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_to_recylcer);
        String address = "https://www.omdbapi.com/?s=rambo&apikey=70ad462a";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(address, new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Gson gson = new Gson();
                MovieList movieList = gson.fromJson(response.toString(), MovieList.class);
                Log.d(TAG, "onSuccess: " + movieList.getSearch().get(1).getTitle());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }
}
