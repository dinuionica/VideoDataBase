package entertainment;

import user.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Information about a season of a tv show
 */
public final class Season {
    /**
     * Number of current season
     */
    private final int currentSeason;
    /**
     * Duration in minutes of a season
     */
    private int duration;
    /**
     * List of ratings for each season
     */
    private List<Double> ratings;

    private final HashMap<User, Double> ratingsByUser;

    public Season(final int currentSeason, final int duration) {
        this.currentSeason = currentSeason;
        this.duration = duration;
        this.ratings = new ArrayList<>();
        this.ratingsByUser = new HashMap<>();
    }

    public Map<User, Double> getRatingsByUser() {
        return ratingsByUser;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(final int duration) {
        this.duration = duration;
    }

    public List<Double> getRatings() {
        return ratings;
    }

    public void setRatings(final List<Double> ratings) {
        this.ratings = ratings;
    }

    /**
     * The method that calculates the average rating of a season
     * @return the rating
     */
    public double getRatingSeason() {
        double sumRatings = 0;
        /* adding each rating from the list to the current amount */
        for (double rating : this.ratings) {
            sumRatings += rating;
        }
        if (this.ratings.size() != 0) {
            return (sumRatings / this.ratings.size());
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Episode{"
                + "currentSeason="
                + currentSeason
                + ", duration="
                + duration
                + '}';
    }
}
