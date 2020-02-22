package com.android.train.task.json;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.train.task.json.imdb.Search;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchedAdapter extends RecyclerView.Adapter<SearchedAdapter.SearchedHolder> {
    List<Search> mlist;
    SearchedAdapter(List<Search> list){
        mlist = list;
    }

    @NonNull
    @Override
    public SearchedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.searched_recycler_item,parent,false);
        SearchedHolder holder = new SearchedHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchedHolder holder, int position) {
        holder.txtSearchedTitle.setText(mlist.get(position).getTitle());
        holder.txtSearchedYear.setText(mlist.get(position).getYear());
        String imageUrl = mlist.get(position).getPoster();
        Picasso.get().load(imageUrl).into(holder.imgSearchedPoster);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class  SearchedHolder extends  RecyclerView.ViewHolder{
        TextView txtSearchedTitle;
        TextView txtSearchedYear;
        ImageView imgSearchedPoster;
        public SearchedHolder(@NonNull View itemView) {
            super(itemView);
            txtSearchedTitle = itemView.findViewById(R.id.txtSearchedTitle);
            txtSearchedYear = itemView.findViewById(R.id.txtSearchedYear);
            imgSearchedPoster = itemView.findViewById(R.id.imgSearchedPoster);
        }
    }
}
