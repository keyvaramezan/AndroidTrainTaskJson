package com.android.train.task.json;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.train.task.json.imdbProp.MovieProperties;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MoveiActivity extends AppCompatActivity {
    private static final String TAG = "MoveiActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //final ArrayList<String> movies = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movei);
        Intent intent = getIntent();
        String imdbId = intent.getStringExtra("id");
        String address = "https://www.omdbapi.com/?i="+imdbId+"&apikey=70ad462a";
        final TextView txtDetailTitle = findViewById(R.id.txtDetailTitle);
        final TextView txtYear = findViewById(R.id.txtYear);
        final TextView txtDirector = findViewById(R.id.txtDirector);
        final TextView txtActors = findViewById(R.id.txtActors);
        final ImageView imgPoster = findViewById(R.id.imgPoster);
        final TextView txtGenre = findViewById(R.id.txtGenre);
        final TextView txtCountry = findViewById(R.id.txtCountry);
        final TextView txtLanguage = findViewById(R.id.txtLanguage);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(address, new  JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Gson gson = new Gson();
                MovieProperties prop = gson.fromJson(response.toString(), MovieProperties.class);
                txtDetailTitle.setText("Title: "+prop.getTitle());
                txtYear.setText("Yeare: "+prop.getYear());
                txtDirector.setText("Director: "+prop.getDirector());
                txtActors.setText("Actors: "+prop.getActors());
                txtGenre.setText("Genre: "+prop.getGenre());
                txtCountry.setText("Country: " + prop.getCountry());
                txtLanguage.setText("Language: " + prop.getLanguage());

                String imageUrl = prop.getPoster();
                Picasso.get().load(imageUrl).into(imgPoster);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });

       /* String address = "https://www.omdbapi.com/?s=rambo&apikey=70ad462a";
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
        });*/

    }
}
