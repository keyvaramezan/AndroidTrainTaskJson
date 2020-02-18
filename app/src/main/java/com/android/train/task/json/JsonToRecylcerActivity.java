package com.android.train.task.json;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_to_recylcer);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");


        String address = "https://www.omdbapi.com/?s="+title+"&apikey=70ad462a";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(address, new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Gson gson = new Gson();
                MovieList movieList = gson.fromJson(response.toString(), MovieList.class);
                RecyclerView moviesRecycler = findViewById(R.id.moviesRecycler);
                MovieAdapter adapter = new MovieAdapter(movieList.getSearch());
                moviesRecycler.setAdapter(adapter);
                moviesRecycler.setLayoutManager(new LinearLayoutManager(JsonToRecylcerActivity.this
                        ,RecyclerView.VERTICAL, false));
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }
}
