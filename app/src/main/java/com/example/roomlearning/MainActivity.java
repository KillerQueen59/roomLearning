package com.example.roomlearning;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.DialogInterface;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private MovieAdapter movieAdapter;
    private ArrayList<Movie> movies = new ArrayList<>();
    private RecyclerView recyclerView;
    private MovieDatabase movieDatabase;
    public static final String TAG="MainActivityTag";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recview);
        movieDatabase = Room.databaseBuilder(getApplicationContext(),MovieDatabase.class,"MovieDB").allowMainThreadQueries().build();
        movies.addAll(movieDatabase.getMovieDao().getMovie());//connect model with dao
    }


    private void createMovie(String title,String description){
        int id = movieDatabase.getMovieDao().addMovie(new Movie(0,title,description));

        Movie movie = movieDatabase.getMovieDao().getMovie(id);
    }


    private void deleteMovie(Movie movie,int position){
        movies.remove(position);
        movieDatabase.getMovieDao().deleteMovie(movie);
        movieAdapter.notifyDataSetChanged();
    }



    public void addAndEditMovie(final boolean isUpdate,final Movie movie,final  int position){
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.add_movie,null);
        AlertDialog.Builder alertDialogUserInput = new AlertDialog.Builder(MainActivity.this);
        alertDialogUserInput.setView(view);

        TextView titleMovie = view.findViewById(R.id.newMovie);
        final EditText newMovieTitle = view.findViewById(R.id.movieTitle);
        final EditText newMovieDescirption = view.findViewById(R.id.movieDescription);

        titleMovie.setText(isUpdate ? "Edit Movie" : "Add Movie");

        if (isUpdate && movie !=null) {// jika sudah ada isinya
            newMovieTitle.setText(movie.getTitle());
            newMovieDescirption.setText(movie.getDescription());
        }
        alertDialogUserInput.setCancelable(false)
                .setPositiveButton(isUpdate ? "Update" : "Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (isUpdate){
                            deleteMovie(movie,position);
                        }
                        else {
                            dialogInterface.cancel();
                        }
                    }
        });

        final AlertDialog alertDialog = alertDialogUserInput.create();
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(newMovieTitle.getText().toString())){
                    Toast.makeText(MainActivity.this, "Enter Contact Name", Toast.LENGTH_SHORT).show();
                }else {
                    alertDialog.dismiss();
                }
            }
        });

    }

}
