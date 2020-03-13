package com.android.train.task.json;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.android.train.task.json.imdb.Search;
import com.android.train.task.json.imdbProp.MovieProperties;

import java.util.ArrayList;
import java.util.List;

public class ImdbDatabase extends SQLiteOpenHelper {
    String TABLE_NAME = "tblMovie";
    public ImdbDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MOVIE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME + "(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "title TEXT,"+
                "year TEXT,"+
                "poster TEXT," +
                "director TEXT," +
                "actors TEXT," +
                "genre TEXT," +
                "country TEXT," +
                "language TEXT" +
                ")";
        db.execSQL(CREATE_MOVIE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertMovie (String title, String year, String poster,
                             String director, String actors, String genre, String country, String language){
        if (title.contains("'"))
        {
            title = title.replace("'", "''");
        }
        String INSERT_MOVIE_QUERY = "INSERT INTO "+TABLE_NAME+"(title,year,poster,director,actors,genre,country,language) " +
                "VALUES ("
                +"'"+ title +"'"+","
                +"'"+ year +"'"+","
                +"'"+ poster +"'" +","
                +"'"+ director +"'" +","
                +"'"+ actors +"'" +","
                +"'"+ genre +"'" +","
                +"'"+ country +"'" +","
                +"'"+ language +"'" +
                ")";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(INSERT_MOVIE_QUERY);
    }
    public List<MovieProperties> getMoviesDB(){
        List<MovieProperties> mlist = new ArrayList<>();
        String GET_ALL_MOVIE = "SELECT title,year,poster,director,actors,genre,country,language FROM "+TABLE_NAME;
        MovieProperties mProperties;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(GET_ALL_MOVIE, null);
        while (c.moveToNext()){
            mProperties = new MovieProperties();
            mProperties.setTitle(c.getString(0));
            mProperties.setYear(c.getString(1));
            mProperties.setPoster(c.getString(2));
            mProperties.setDirector(c.getString(3));
            mProperties.setActors(c.getString(4));
            mProperties.setGenre(c.getString(5));
            mProperties.setCountry(c.getString(6));
            mProperties.setLanguage(c.getString(7));
            mlist.add(mProperties);
        }

    return mlist;
    }

}
