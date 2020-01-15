package com.example.roomlearning;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MovieDao {

    @Insert
    public int addMovie(Movie movie);

    @Query("select * from movies")//milih query dari movie
    public List<Movie> getMovie();

    @Query("select * from movies where movie_id ==:movieId")// apa bedanya cok
    public Movie getMovie(int movieId);

    @Update
    public void updateMovie(Movie movie);

    @Delete
    public void deleteMovie(Movie movie);
}
