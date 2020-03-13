package com.android.train.task.json;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.train.task.json.imdb.Search;
import com.android.train.task.json.imdbProp.MovieProperties;

import java.util.ArrayList;
import java.util.List;

public class SearchedMovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched_movie);
        RecyclerView recycler = findViewById(R.id.searchedRecycler);
        final ImdbDatabase db = new ImdbDatabase(SearchedMovieActivity.this, "Imdb", null, 1);
        List<MovieProperties> searchedList = new ArrayList<>();
        searchedList = db.getMoviesDB();
        SearchedAdapter adapter = new SearchedAdapter(searchedList);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(SearchedMovieActivity.this
                , RecyclerView.VERTICAL, false));
    }
}
