package com.android.train.task.json;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.android.train.task.json.imdb.Search;
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
        holder.txtTitle.setText("Title: " + Title);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder{
        TextView txtTitle;

        public MovieViewHolder(@NonNull final View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Search search = mlist.get(getAdapterPosition());
                    Intent intent = new Intent(itemView.getContext(), MoveiActivity.class);
                    intent.putExtra("id", search.getImdbID());
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
