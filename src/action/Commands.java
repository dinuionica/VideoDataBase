package action;

import database.MoviesDataBase;
import database.SerialsDataBase;
import database.UsersDataBase;
import entertainment.Season;
import fileio.ActionInputData;
import show.Movie;
import show.Serial;
import user.User;
import java.util.ArrayList;
import java.util.Map;

/**
 * The class that is used to implement commands
 */
public final class Commands {

    /* default constructor */
    private Commands() {

    }

    /**
     * The method that applies the favorites command to a video
     * @param usersData database class for users
     * @param action the action received as input
     * @return the resulting message
     */
    public static String favoriteCommand(final UsersDataBase usersData,
                                         final ActionInputData action) {
        /* get the data from the action */
        String desiredNameUser = action.getUsername();
        String showTitle = action.getTitle();
        String resultMessage;

        /* get  the necessary data for the current user */
        User currentUser = usersData.findUserDataBase(desiredNameUser);
        assert currentUser != null;
        Map<String, Integer> userHistory = currentUser.getHistory();
        ArrayList<String> currentFavoriteList = currentUser.getFavoriteMovies();

        /* if there is no video in the user's history,displays the message */
        if (!userHistory.containsKey(showTitle)) {
            resultMessage = "error -> " + showTitle + " is not seen";
            return resultMessage;
        }

        /* if I have already found it in the list, it displays the message */
        if (currentFavoriteList.contains(showTitle)) {
            resultMessage = "error -> " + showTitle + " is already in favourite list";
            return resultMessage;
        }

        /* if it doesn't exist, I add it to the list of favorite videos and display the message */
        currentFavoriteList.add(showTitle);
        resultMessage =  "success -> " + showTitle + " was added as favourite";
        return  resultMessage;
    }

    /**
     * The method that applies the view command to a video
     * @param usersData database class for users
     * @param action action
     * @return the resulting message
     */
    public static String viewCommand(final UsersDataBase usersData, final ActionInputData action) {
        /* get  the necessary data for the current user */
        String desiredNameUser = action.getUsername();
        String showTitle = action.getTitle();
        String resultMessage;

        /* get  the necessary data for the current user */
        User currentUser = usersData.findUserDataBase(desiredNameUser);
        assert currentUser != null;
        Map<String, Integer> userHistory = currentUser.getHistory();

        /* if the video has not been viewed */
        if (userHistory.get(showTitle) == null) {
            userHistory.put(showTitle, 1);
            resultMessage = "success -> " +  showTitle + " was viewed with total views of 1";

        } else {
            /* if it was seen I update the number of views and display the desired message */
            Integer numberViews = userHistory.get(showTitle);
            numberViews++;
            userHistory.replace(showTitle, numberViews);
            resultMessage = "success -> " +  showTitle + ""
                            + " was viewed with total views of " + numberViews;
        }
        return  resultMessage;
    }

    /**
     * The method that applies the rating command to a video
     * @param usersData database class for users
     * @param moviesData database class for movies
     * @param serialsData database class for serials
     * @param action the action received as input
     * @return the resulting message
     */
    public static String ratingCommand(final UsersDataBase usersData,
                                       final MoviesDataBase moviesData,
                                       final SerialsDataBase serialsData,
                                       final ActionInputData action) {

        /* get  the necessary data for the current user */
        String desiredNameUser = action.getUsername();
        String showTitle = action.getTitle();

        /* get  the necessary data for the current user */
        User currentUser = usersData.findUserDataBase(desiredNameUser);
        assert currentUser != null;
        Map<String, Integer> userHistory = currentUser.getHistory();
        String resultMessage;

        /* if the video has not been viewed */
        if (userHistory.get(showTitle) == null) {
            resultMessage = "error -> " + showTitle + " is not seen";
            return resultMessage;
        }

        /* if the video has not been viewed and if is a movie */
        if (action.getSeasonNumber() == 0) {
            Movie desiredMovie = moviesData.findMovieDataBase(showTitle);
            assert desiredMovie != null;
            if (!desiredMovie.getRatingByUser().containsKey(currentUser)) {
                desiredMovie.getRatingByUser().put(currentUser, action.getGrade());
                currentUser.incrementGivenRatingNumber();
                resultMessage = "success -> " + showTitle + " was rated with "
                                + action.getGrade() + " by " + currentUser.getUsername();
                return resultMessage;
            }
        }

        /* if the video has not been viewed and if is a serial */
        /* looking for the desired series and season */
        Serial desiredSerial = serialsData.findSerialDataBase(showTitle);
        if (desiredSerial != null) {
            ArrayList<Season> seasonsList = desiredSerial.getSeasons();
            Season desiredSeason = seasonsList.get(action.getSeasonNumber() - 1);

            /* If the user did not give a rating, apply the rating on the
             * video and update the number of ratings given by the user
             */
            if (!desiredSeason.getRatingsByUser().containsKey(currentUser)) {
                desiredSeason.getRatingsByUser().put(currentUser, action.getGrade());
                desiredSeason.getRatings().add(action.getGrade());
                currentUser.incrementGivenRatingNumber();
                /* return result message */
                resultMessage = "success -> " + showTitle + " was rated with "
                                + action.getGrade() +  " by " + currentUser.getUsername();
                return resultMessage;
            }
        }

        /* if the video has been already rated, display the desired message */
        resultMessage = "error -> " + showTitle + " has been already rated";
        return resultMessage;
    }
}
