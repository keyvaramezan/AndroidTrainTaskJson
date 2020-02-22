package com.android.train.task.json;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.android.train.task.json.imdb.Search;

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
                "poster TEXT" +
                ")";
        db.execSQL(CREATE_MOVIE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertMovie (String title, String year, String poster){
        if (title.contains("'"))
        {
            title = title.replace("'", "''");
        }
        String INSERT_MOVIE_QUERY = "INSERT INTO "+TABLE_NAME+"(title,year,poster) VALUES ("
                +"'"+ title +"'"+","
                +"'"+ year +"'"+","
                +"'"+ poster +"'" +
                ")";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(INSERT_MOVIE_QUERY);
    }
    public List<Search> getMoviesDB(){
        List<Search> mlist = new ArrayList<>();
        String GET_ALL_MOVIE = "SELECT title,year,poster FROM "+TABLE_NAME;
        Search mSearch;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(GET_ALL_MOVIE, null);
        while (c.moveToNext()){
            mSearch = new Search();
            mSearch.setTitle(c.getString(0));
            mSearch.setYear(c.getString(1));
            mSearch.setPoster(c.getString(2));
            mlist.add(mSearch);
        }

    return mlist;
    }

}
