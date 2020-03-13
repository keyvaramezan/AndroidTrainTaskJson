package com.android.train.task.json;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.train.task.json.imdb.MovieList;
import com.android.train.task.json.imdb.Search;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class JsonToRecylcerActivity extends AppCompatActivity {

    private static final String TAG = "MoveiActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_to_recylcer);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        final Button btnSaveToDb = findViewById(R.id.btnSaveToDb);

        String address = "https://www.omdbapi.com/?s="+title+"&apikey=70ad462a";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(address, new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Gson gson = new Gson();
                final MovieList movieList = gson.fromJson(response.toString(), MovieList.class);
                if (movieList.getSearch() != null) {
                    RecyclerView moviesRecycler = findViewById(R.id.moviesRecycler);
                    MovieAdapter adapter = new MovieAdapter(movieList.getSearch());
                    moviesRecycler.setAdapter(adapter);
                    moviesRecycler.setLayoutManager(new LinearLayoutManager(JsonToRecylcerActivity.this
                            , RecyclerView.VERTICAL, false));

                }
                else
                {
                    Toast.makeText(JsonToRecylcerActivity.this, "No movies found", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }
}
