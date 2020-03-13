package com.android.train.task.json;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    final ImdbDatabase db = new ImdbDatabase(MoveiActivity.this, "Imdb", null, 1);
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
                Button btnSaveToDb = findViewById(R.id.btnSaveToDb);
                Gson gson = new Gson();
                MovieProperties prop = gson.fromJson(response.toString(), MovieProperties.class);
                final String  title = prop.getTitle();
                final String year = prop.getYear();
                final String poster = prop.getPoster();
                final String director = prop.getDirector();
                final String genre = prop.getGenre();
                final String actors = prop.getActors();
                final String country = prop.getCountry();
                final String language = prop.getLanguage();
                txtDetailTitle.setText("Title: "+title);
                txtYear.setText("Yeare: "+year);
                txtDirector.setText("Director: "+director);
                txtActors.setText("Actors: "+actors);
                txtGenre.setText("Genre: "+genre);
                txtCountry.setText("Country: " + country);
                txtLanguage.setText("Language: " + language);
                String imageUrl = poster;
                Picasso.get().load(imageUrl).into(imgPoster);
                btnSaveToDb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.insertMovie(title,year,poster,director,actors,genre,country,language);
                    }
                });


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
