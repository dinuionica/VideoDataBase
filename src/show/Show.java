package show;

import database.UsersDataBase;
import fileio.ActionInputData;
import user.User;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class of a show
 */
public abstract class Show {
    /**
     * Show's title
     */
    private final String title;
    /**
     * The year the show was released
     */
    private final int year;
    /**
     * Show casting
     */
    private final ArrayList<String> cast;
    /**
     * Show genres
     */
    private final ArrayList<String> genres;
    /**
     * Number of views
     */
    private int numberViews;
    /**
     * Number of apparition
     */
    private int numberFavoritesAppearances;

    /* constructor */
    public Show(final String title, final int year,
                final ArrayList<String> cast,
                final ArrayList<String> genres) {
        this.title = title;
        this.year = year;
        this.cast = cast;
        this.genres = genres;
        this.numberViews = 0;
        this.numberFavoritesAppearances = 0;
    }

    /* getters and setters */
    public final String getTitle() {
        return title;
    }

    public final int getYear() {
        return year;
    }

    public final ArrayList<String> getCast() {
        return cast;
    }

    public final ArrayList<String> getGenres() {
        return genres;
    }

    /**
     * The method that returns the number of appearances in the favorite genres
     * @return number of appearances
     */
    public int getNumberFavoritesAppearances() {
        return numberFavoritesAppearances;
    }

    /**
     * The method that returns the number of views
     * @return the number of views
     */
    public int getNumberViews() {
        return numberViews;
    }

    /**
     * The method that calculates the sum of views of a show
     * @param usersData database class for users
     */
    public void calculateSumViews(final UsersDataBase usersData) {
        /* for each user, if he watched the video
         * update the number of views of the current video
         */
        for (User user : usersData.getUsersList()) {
            if (user.getHistory().containsKey(this.getTitle())) {
                this.numberViews += user.getHistory().get(this.getTitle());
            }
        }
    }

    /**
     * The method that checks filters for a show
     * @param action the action received as input
     * @return true or false
     */
    public boolean filter(final ActionInputData action) {
        List<List<String>> listFilters = action.getFilters();
        List<String> yearsList = listFilters.get(0);
        List<String> genresList = listFilters.get(1);

        /* checking the first filter -> year */
        if (yearsList.get(0) != null) {
            if (this.year != Integer.parseInt(yearsList.get(0))) {
                return false;
            }
        }

        /* checking the second filter -> genres list */
        for (String inputGenre: genresList) {
            /* if the second filter does not exist */
            if (inputGenre == null) {
                return true;
            }
            if (this.getGenres().contains(inputGenre)) {
                return true;
            }
        }
        return false;
    }

    /**
     * The method that calculates the number of favorite appearances
     * @param usersData database class for users
     */
    public void calculateFavoritesAppearances(final UsersDataBase usersData) {
        /* for each user, it is checked if the video is in the list of
         * favorite videos, and if so we update the number of appearances
         */
        for (User user : usersData.getUsersList()) {
            for (String favoriteShow : user.getFavoriteMovies()) {
                if (favoriteShow.equals(this.getTitle())) {
                    this.numberFavoritesAppearances++;
                }
            }
        }
    }
    /**
     * The abstract method that calculates the duration of a show
     * @return the duration
     */
    public abstract int getDuration();

    /**
     * The abstract method that calculates the average rating of a show
     * @return the rating
     */
    public abstract double getAverageRating();
}
