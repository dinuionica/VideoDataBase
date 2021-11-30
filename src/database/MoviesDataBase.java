package database;

import show.Movie;
import java.util.ArrayList;

/**
 * The class used to create the database movies list
 */
public final class MoviesDataBase {

    private ArrayList<Movie> moviesList;

    /* constructor */
    public MoviesDataBase(final ArrayList<Movie> moviesList) {
        this.moviesList = moviesList;
    }

    /* setters and getters */
    public ArrayList<Movie> getMoviesList() {
        return moviesList;
    }

    public void setMoviesList(final ArrayList<Movie> moviesList) {
        this.moviesList = moviesList;
    }

    /**
     * The method that finds a movie in database by a desire name
     * @param desiredName the name of the movie
     * @return the desired movie
     */
    public Movie findMovieDataBase(final String desiredName) {
        for (Movie movie: this.moviesList) {
            if (movie.getTitle().equals(desiredName)) {
                return movie;
            }
        }
        return null;
    }
}
