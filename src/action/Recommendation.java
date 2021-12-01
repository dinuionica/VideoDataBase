package action;

import database.GenresDataBase;
import fileio.ActionInputData;
import show.Genre;
import show.Show;
import user.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import database.ShowsDataBase;
import database.UsersDataBase;


/**
 * The class that is used to implement recommendation
 */
public final class Recommendation {
    /* default constructor */
    private Recommendation() {

    }

    /**
     * The method that check if a user has BASIC subscription
     * @param usersData database class for users
     * @param action the action received as input
     * @return true/false
     */
    public static boolean checkSubscriptionType(final UsersDataBase usersData,
                                                final ActionInputData action) {
        User desireUser = usersData.findUserDataBase(action.getUsername());
        assert desireUser != null;
        if (desireUser.getSubscriptionType().equals("BASIC")) {
            return false;
        }
        return true;
    }
    /**
     * The method that applies a standard recommendation
     * @param usersData database class for users
     * @param showsData database class of shows
     * @param action the action received as input
     * @return the resulting message
     */
    public static String standardRecommendation(final UsersDataBase usersData,
                                                final ShowsDataBase showsData,
                                                final ActionInputData action) {
        /* find the desired user for which the recommendation applies */
        User desiredUser = usersData.findUserDataBase(action.getUsername());
        ArrayList<Show> showsList = showsData.getShowsList();
        String resultMessage;

        for (Show show: showsList) {
            /* if the user did not watch the video, return it */
            if (!desiredUser.getHistory().containsKey(show.getTitle())) {
                resultMessage = "StandardRecommendation result: " + show.getTitle();
                return resultMessage;
            }
        }
        return null;
    }

    /**
     * The method that applies best unseen recommendation
     * @param usersData database class for users
     * @param showsData database class of shows
     * @param action the action received as input
     * @return the resulting message
     */
    public static String bestUnseenRecommendation(final UsersDataBase usersData,
                                                  final ShowsDataBase showsData,
                                                  final ActionInputData action) {
        ArrayList<Show> showsList = showsData.getShowsList();
        /* sorting the list by average rating and by index of database list */
        Collections.sort(showsList,
                Comparator.comparing(Show::getAverageRating)
                          .thenComparing(showsList::indexOf));

        /* reversing the list because we need to find the best video */
        Collections.reverse(showsList);
        String resultMessage;

        /* find the desired user from database */
        User desiredUser = usersData.findUserDataBase(action.getUsername());
        for (Show show: showsList) {
            /* if the user did not watch the video, return it */
            assert desiredUser != null;
            if (!desiredUser.getHistory().containsKey(show.getTitle())) {
                resultMessage = "BestRatedUnseenRecommendation result: " + show.getTitle();
                return resultMessage;
            }
        }
        return null;
    }

    /**
     * The method that applies a search recommendation
     * @param usersData database class for users
     * @param showsData  database class of shows
     * @param action the action received as input
     * @return the resulting message
     */
    public static String searchRecommend(final UsersDataBase usersData,
                                         final ShowsDataBase showsData,
                                         final ActionInputData action) {
        /* check subscription type */
        if (!checkSubscriptionType(usersData, action)) {
            return null;
        }

        /* find the desired user from database */
        User user = usersData.findUserDataBase(action.getUsername());
        ArrayList<Show> showsList = showsData.getShowsList();

        /* sorting the list by average rating and by title */
        Collections.sort(showsList,
                Comparator.comparing(Show::getAverageRating)
                          .thenComparing(Show::getTitle));

        String resultMessage;
        ArrayList<String> resultList = new ArrayList<>();

        for (Show show: showsList) {
            if (show.getGenres().contains(action.getGenre())) {
                /* if the user did not watch the video with this genre,
                 * add it to the final list with the shows name
                 */
                assert user != null;
                if (user.getHistory().get(show.getTitle()) == null) {
                    resultList.add(show.getTitle());
                }
            }
        }

        /* if no recommendation could be found, the corresponding message is displayed */
        if (resultList.size() != 0) {
            resultMessage = "SearchRecommendation result: " + resultList;
            return resultMessage;
        }
        return null;
    }

    /**
     * The method that applies a favorite recommendation
     * @param usersData database class for users
     * @param action the action received as input
     * @return the resulting message
     */
    public static String favoriteRecommendation(final UsersDataBase usersData,
                                                final ShowsDataBase showsData,
                                                final ActionInputData action) {
        /* check subscription type */
        if (!checkSubscriptionType(usersData, action)) {
            return null;
        }
        ArrayList<Show> showsList = showsData.getShowsList();
        /* calculating the number of favorite appearances for each video */
        for (Show show: showsList) {
            show.calculateFavoritesAppearances(usersData);
        }

        /* find the desired user from database */
        User desiredUser = usersData.findUserDataBase(action.getUsername());

        /* sorting the list by number of appearances and by index of database list */
        Collections.sort(showsList,
                Comparator.comparing(Show::getNumberFavoritesAppearances)
                          .thenComparing(showsList::indexOf));

        Collections.reverse(showsList);
        for (Show show: showsList) {
            if (show.getNumberFavoritesAppearances() != 0) {
                /* if the user did not watch the video, return it */
                assert desiredUser != null;
                if (!desiredUser.getHistory().containsKey(show.getTitle())) {
                    String resultMessage = "FavoriteRecommendation result: " + show.getTitle();
                    return resultMessage;
                }
            }
        }
        return null;
    }
    /**
     * The method that applies a popular recommendation
     * @param usersData database class for users
     * @param showsData database class of shows
     * @param genresData database class of genres
     * @param action the action received as input
     * @return the resulting message
     */
    public static String popular(final UsersDataBase usersData,
                                 final ShowsDataBase showsData,
                                 final GenresDataBase genresData,
                                 final ActionInputData action) {

        /* check subscription type */
        if (!checkSubscriptionType(usersData, action)) {
            return null;
        }

        /* obtaining lists of shows and genres */
        ArrayList<Show> showsList = showsData.getShowsList();
        ArrayList<Genre> genresLit = genresData.getGenresList();
        String resultMessage;

        /* for each genre the popularity is calculated */
        for (Genre genre : genresLit) {
            genre.calculatePopularityFactor(showsData);
        }

        /* sorting the genres list by factor of popularity and by name */
        Collections.sort(genresLit,
                Comparator.comparing(Genre::getPopularityFactor));

        /* sorting the shows list by index of the database  */
        Collections.sort(showsList,
                Comparator.comparing(showsList::indexOf));

        /* find the desired user from database */
        User desiredUser = usersData.findUserDataBase(action.getUsername());

        Collections.reverse(genresLit);
        for (Genre genre : genresLit) {
            for (Show show : showsList) {
                if (show.getGenres().contains(genre.getNameGenre())) {
                    /* if the user has not watched this video, which is
                     * the most popular, return the current video
                     */
                    if (!desiredUser.getHistory().containsKey(show.getTitle())) {
                        resultMessage = "PopularRecommendation result: " + show.getTitle();
                        return resultMessage;
                    }
                }
            }
        }
        return null;
    }
}
