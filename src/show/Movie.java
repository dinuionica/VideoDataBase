package show;

import fileio.MovieInputData;
import user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class Movie extends Show {
    /**
     * Duration in minutes of a season
     */
    private final int duration;
    private final Map<User, Double> ratingByUser;

    /* constructor */
    public Movie(final String title, final int year, final ArrayList<String> cast,
                 final ArrayList<String> genres, final int duration) {
        super(title, year, cast, genres);
        this.duration = duration;
        this.ratingByUser = new HashMap<>();
    }

    public Movie(final MovieInputData movie) {
        super(movie.getTitle(), movie.getYear(), movie.getCast(), movie.getGenres());
        this.duration = movie.getDuration();
        this.ratingByUser = new HashMap<>();
    }

    /* getter */
    public int getDuration() {
        return duration;
    }

    public Map<User, Double> getRatingByUser() {
        return ratingByUser;
    }

    /**
     * function
     * @return
     */
    public double getAverageRatings() {

        double sumRating = 0;
        for (double x : ratingByUser.values()) {
            sumRating += x;
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
