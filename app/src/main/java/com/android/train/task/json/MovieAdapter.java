package com.android.train.task.json;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.train.task.json.imdb.MovieList;
import com.android.train.task.json.imdb.Search;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    List<Search> mlist;
    MovieAdapter(List<Search> list){
        mlist = list;
    }
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.recycler_item, parent, false);
        MovieViewHolder holder = new MovieViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        String Title = mlist.get(position).getTitle();
        String year = mlist.get(position).getYear();
        holder.txtTitle.setText("Title: " + Title);
        holder.txtYear.setText("Year: " + year);
        String imagUrl = mlist.get(position).getPoster();
        Picasso.get().load(imagUrl).into(holder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder{
        TextView txtTitle;
        TextView txtYear;
        ImageView imgPoster;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtYear = itemView.findViewById(R.id.txtYear);
            imgPoster = itemView.findViewById(R.id.imgPoster);
        }
    }
}
