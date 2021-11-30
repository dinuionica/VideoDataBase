package show;

import fileio.MovieInputData;
import user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class of a movie
 */
public final class Movie extends Show {
    /**
     * Duration in minutes of a season
     */
    private final int duration;
    /**
     * The rating given by a user
     */
    private final HashMap<User, Double> ratingByUser;

    /* constructor */
    public Movie(final String title, final int year, final ArrayList<String> cast,
                 final ArrayList<String> genres, final int duration) {
        super(title, year, cast, genres);
        this.duration = duration;
        this.ratingByUser = new HashMap<>();
    }

    /* the constructor that receives as argument a user MovieInput type */
    public Movie(final MovieInputData movie) {
        super(movie.getTitle(), movie.getYear(), movie.getCast(), movie.getGenres());
        this.duration = movie.getDuration();
        this.ratingByUser = new HashMap<>();
    }

    /* getters */
    public int getDuration() {
        return duration;
    }

    public Map<User, Double> getRatingByUser() {
        return ratingByUser;
    }

    /**
     * The method that calculates average rating of a movie
     * @return the rating
     */
    public double getAverageRating() {
        /* calculate the sum of the ratings given by the users */
        double sumRating = 0;
        for (Map.Entry<User, Double> entry : this.ratingByUser.entrySet())  {
            sumRating += entry.getValue();
        }
        if (ratingByUser.size() == 0) {
            return 0;
        }
        return (sumRating / ratingByUser.size());
    }

    @Override
    public String toString() {
        return "MovieInputData{" + "title= "
                + super.getTitle() + "year= "
                + super.getYear() + "duration= "
                + duration + "cast {"
                + super.getCast() + " }\n"
                + "genres {" + super.getGenres() + " }\n ";
    }
}
