package show;

import database.ShowsDataBase;
import database.UsersDataBase;
import fileio.ActionInputData;
import user.User;
import java.util.ArrayList;
import java.util.List;

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

    private int numberViews;

    private int numberApparition;

    /* constructor */
    public Show(final String title, final int year,
                final ArrayList<String> cast,
                final ArrayList<String> genres) {
        this.title = title;
        this.year = year;
        this.cast = cast;
        this.genres = genres;
        this.numberViews = 0;
        this.numberApparition = 0;
    }

    /* getters */
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
     * fas
     * @param usersData
     */
    public void calculateSumViews(final UsersDataBase usersData) {
        for (User user : usersData.getUsersList()) {
            if (user.getHistory().containsKey(this.getTitle())) {
                this.numberViews += user.getHistory().get(this.getTitle());
            }
        }
    }

    /**
     * dsa
     * @return
     */
    public int getNumberViews() {
        return numberViews;
    }

    /**
     * das
     * @param action
     * @return
     */
    public boolean filter(final ActionInputData action) {
        List<List<String>> listFilters = action.getFilters();
        List<String> yearsList = listFilters.get(0);
        List<String> genresList = listFilters.get(1);

        if (yearsList.get(0) != null) {
            if (this.year != Integer.parseInt(yearsList.get(0))) {
                return false;
            }
        }
        for (String inputGenre: genresList) {
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
     * final
     */

    public void calculatenumberFavoriteApparition(final UsersDataBase usersData) {
        for (User user : usersData.getUsersList()) {
            for (String favoriteShow : user.getFavoriteMovies()) {
                if (favoriteShow.equals(this.getTitle())) {
                    this.numberApparition++;
                }
            }
        }
    }
    /**
     *
     * @return
     */
    public int getNumberApparition() {
        return numberApparition;
    }

    /**
     * sfa
     * @return
     */
    public abstract int getDuration();

    /**
     * fsa
     * @return
     */
    public abstract double getAverageRatings();

    public boolean pGenre(final String name) {
        for (String nameString : getGenres()) {
            if (nameString.equals(name)) {
                return true;
            }
        }
        return false;
    }
}

