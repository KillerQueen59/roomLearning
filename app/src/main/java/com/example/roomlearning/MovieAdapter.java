package com.example.roomlearning;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private ArrayList<Movie> movies = new ArrayList<>();
    private Context context;
    private MainActivity mainActivity;


    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new MovieAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
        holder.titleMovie.setText(movies.get(position).getTitle());
        holder.descroiptionMovie.setText(movies.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleMovie;
        private TextView descroiptionMovie;
        private LinearLayout layoutMovie;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleMovie = itemView.findViewById(R.id.titleMovie);
            descroiptionMovie = itemView.findViewById(R.id.descriptionMovie);
            layoutMovie = itemView.findViewById(R.id.layoutMovie);
        }
    }
}
