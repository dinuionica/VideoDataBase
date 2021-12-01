package action;

import actor.Actor;
import fileio.ActionInputData;
import show.Movie;
import show.Serial;
import user.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import database.ActorsDataBase;
import database.MoviesDataBase;
import database.SerialsDataBase;
import database.UsersDataBase;

public final class Query {
    /* default constructor */
    private Query() {

    }

    /**
     * The method that applies the average query
     * @param usersData database class for users
     * @param actorsData database class of actors
     * @param moviesData database class of movies
     * @param serialsData database class of serials
     * @param action the action received as input
     * @return the resulting message
     */
    public static String averageQuery(final UsersDataBase usersData,
                                      final ActorsDataBase actorsData,
                                      final MoviesDataBase moviesData,
                                      final SerialsDataBase serialsData,
                                      final ActionInputData action) {
        /* creating the necessary lists */
        ArrayList<String> resultList = new ArrayList<>();
        ArrayList<Actor> actorsList = actorsData.getActorList();

        /* for each actor, calculate the rating based on the videos in which he played */
        for (Actor actor : actorsList) {
            actor.calculateRatingActor(moviesData, serialsData);
        }

        /* sorting the list by average rating and by name */
        Collections.sort(actorsList, Comparator.comparing(Actor::getAverageRating)
                        .thenComparing(Actor::getName));

        /* reversing the list according to the sort parameter received as input */
        if (action.getSortType().equals("desc")) {
            Collections.reverse(actorsList);
        }

        /* adding to the list the first n actors */
        int i = 0;
        for (Actor actor : actorsList) {
                if (actor.getAverageRating() != 0 && i < action.getNumber()) {
                    resultList.add(actor.getName());
                    i++;
            }
        }

        String resultMessage = "Query result: " + resultList;
        return resultMessage;
    }

    /**
     * The method that applies the awards query
     * @param usersData database class for users
     * @param actorsData database class of actors
     * @param action the action received as input
     * @return the resulting message
     */
    public static String awardsQuery(final UsersDataBase usersData, final ActorsDataBase actorsData,
                                     final ActionInputData action) {
        /* creating the necessary lists */
        ArrayList<String> resultList = new ArrayList<>();
        ArrayList<Actor> actorsList = actorsData.getActorList();

        /* for each actor, calculate the number of awards */
        for (Actor actor : actorsList) {
            actor.calculateNumberAwards();
        }

        /* sorting the list by total number of awards  and by name */
        Collections.sort(actorsList, Comparator.comparing(Actor::getNumberAwards)
                        .thenComparing(Actor::getName));

        /* reversing the list according to the sort parameter received as input */
        if (action.getSortType().equals("desc")) {
            Collections.reverse(actorsList);
        }

        /* adding to the final list all actors who have all the necessary awards */
        for (Actor actor : actorsList) {
            if (actor.checkAllAwardsActor(action)) {
                resultList.add(actor.getName());
            }
        }

        String resultMessage = "Query result: " + resultList;
        return resultMessage;
    }

    /**
     * The method that applies the filter description query
     * @param actorsData database class of actors
     * @param action the action received as input
     * @return the resulting message
     */
    public static String filterDescriptionQuery(final ActorsDataBase actorsData,
                                                final ActionInputData action) {
        /* creating the necessary lists */
        ArrayList<String> resultList = new ArrayList<>();
        ArrayList<Actor> actorsList = actorsData.getActorList();

        /* sorting the list by name */
        Collections.sort(actorsList, Comparator.comparing(Actor::getName));

        /* reversing the list according to the sort parameter received as input */
        if (action.getSortType().equals("desc")) {
            Collections.reverse(actorsList);
        }

        /* adding to the final list all actors who have all the necessary filters */
        for (Actor actor: actorsList) {
            if (actor.checkDescriptionActor(action)) {
                resultList.add(actor.getName());
            }
        }

        String resultMessage = "Query result: " + resultList;
        return resultMessage;
    }

    /**
     * The method that applies the rating query to movies
     * @param moviesData database class of movies
     * @param action the action received as input
     * @return the resulting message
     */
    public static String moviesRatingQuery(final MoviesDataBase moviesData,
                                           final ActionInputData action) {

        /* creating the necessary lists */
        ArrayList<String> resultList = new ArrayList<>();
        ArrayList<Movie> moviesList = moviesData.getMoviesList();

        /* sorting the list by the average rating and by name */
        Collections.sort(moviesList, Comparator.comparing(Movie::getAverageRating)
                        .thenComparing(Movie::getTitle));

        /* reversing the list according to the sort parameter received as input */
        if (action.getSortType().equals("desc")) {
            Collections.reverse(moviesList);
        }

        /* adding to the list the first n movies */
        int i = 0;
        for (Movie movie : moviesList) {
            if (movie.getAverageRating() != 0 && movie.filter(action) && i < action.getNumber()) {
                resultList.add(movie.getTitle());
            }
        }

        String resultMessage = "Query result: " + resultList;
        return resultMessage;
    }


    /**
     * The method that applies the rating query to serials
     * @param serialsData database class of serials
     * @param action the action received as input
     * @return the resulting message
     */
    public static String serialsRatingQuery(final SerialsDataBase serialsData,
                                            final ActionInputData action) {
        /* creating the necessary lists */
        ArrayList<String> resultList = new ArrayList<>();
        ArrayList<Serial> serialsList = serialsData.getSerialsList();

        /* sorting the list by the average rating and by name */
        Collections.sort(serialsList, Comparator.comparing(Serial::getAverageRating)
                        .thenComparing(Serial::getTitle));

        /* reversing the list according to the sort parameter received as input */
        if (action.getSortType().equals("desc")) {
            Collections.reverse(serialsList);
        }

        /* adding to the list the first n serials */
        int i = 0;
        for (Serial serial : serialsList) {
            if (serial.getAverageRating() != 0 && serial.filter(action) && i < action.getNumber()) {
                resultList.add(serial.getTitle());
            }
        }

        String resultMessage = "Query result: " + resultList;
        return resultMessage;
    }

    /**
     * The method that applies the favorite query to movies
     * @param moviesData database class of movies
     * @param action the action received as input
     * @return the resulting message
     */
    public static String moviesFavoriteQuery(final UsersDataBase usersData,
                                             final MoviesDataBase moviesData,
                                             final ActionInputData action) {
        /* creating the necessary lists */
        ArrayList<String> resultList = new ArrayList<>();
        ArrayList<Movie> moviesList = moviesData.getMoviesList();

        /* for each movie, calculate the number of favorite appearances */
        for (Movie movie: moviesList) {
            movie.calculateFavoritesAppearances(usersData);
        }

        /* sorting the list by the number of favorite appearances and by name */
        Collections.sort(moviesList, Comparator.comparing(Movie::getNumberFavoritesAppearances)
                        .thenComparing(Movie::getTitle));

        /* reversing the list according to the sort parameter received as input */
        if (action.getSortType().equals("desc")) {
            Collections.reverse(moviesList);
        }

        /* adding to the list the first n movies */
        int i = 0;
        for (Movie movie : moviesList) {
            if (movie.getNumberFavoritesAppearances() > 0 && movie.filter(action)
                    && i < action.getNumber()) {
                resultList.add(movie.getTitle());

            }
        }
        String resultMessage = "Query result: " + resultList;
        return resultMessage;
    }

    /**
     * The method that applies the favorite query to movies
     * @param serialsData database class of serials
     * @param action the action received as input
     * @return the resulting message
     */
    public static String serialsFavoriteQuery(final UsersDataBase usersData,
                                              final SerialsDataBase serialsData,
                                              final ActionInputData action) {
        /* creating the necessary lists */
        ArrayList<String> resultList = new ArrayList<>();
        ArrayList<Serial> serialsList = serialsData.getSerialsList();

        /* for each serial, calculate the number of favorite appearances */
        for (Serial serial : serialsData.getSerialsList()) {
            serial.calculateFavoritesAppearances(usersData);
        }

        /* sorting the list by the number of favorite appearances and by name */
        Collections.sort(serialsData.getSerialsList(),
                Comparator.comparing(Serial::getNumberFavoritesAppearances)
                        .thenComparing(Serial::getTitle));

        /* reversing the list according to the sort parameter received as input */
        if (action.getSortType().equals("desc")) {
            Collections.reverse(serialsData.getSerialsList());
        }

        /* adding to the list the first n serials */
        int i = 0;
        for (Serial serial : serialsData.getSerialsList()) {
                if (serial.getNumberFavoritesAppearances() > 0 && serial.filter(action)
                        && i < action.getNumber()) {
                    resultList.add(serial.getTitle());
            }
        }
        String resultMessage = "Query result: " + resultList;
        return resultMessage;
    }


    /**
     * The method that applies the longest query to movies
     * @param moviesData database class of movies
     * @param action the action received as input
     * @return the resulting message
     */
    public static String moviesLongestQuery(final MoviesDataBase moviesData,
                                            final ActionInputData action) {
        /* creating the necessary lists */
        ArrayList<String> resultList = new ArrayList<>();
        ArrayList<Movie> moviesList = moviesData.getMoviesList();

        /* sorting the list by duration of a movies and by name */
        Collections.sort(moviesList, Comparator.comparing(Movie::getDuration)
                        .thenComparing(Movie::getTitle));

        /* reversing the list according to the sort parameter received as input */
        if (action.getSortType().equals("desc")) {
            Collections.reverse(moviesList);
        }

        /* adding to the list the first n movies */
        int i = 0;
        for (Movie movie : moviesList) {
            if (movie.getDuration() != 0 && movie.filter(action) && i < action.getNumber()) {
                resultList.add(movie.getTitle());
            }
        }

        String resultMessage = "Query result: " + resultList;
        return resultMessage;
    }

    /**
     * The method that applies the longest query to serials
     * @param serialsData database class of serials
     * @param action the action received as input
     * @return the resulting message
     */
    public static String serialsLongestQuery(final SerialsDataBase serialsData,
                                             final ActionInputData action) {
        /* creating the necessary lists */
        ArrayList<String> resultList = new ArrayList<>();
        ArrayList<Serial> serialsList = serialsData.getSerialsList();

        /* sorting the list by duration of a serial and by name */
        Collections.sort(serialsList, Comparator.comparing(Serial::getDuration)
                        .thenComparing(Serial::getTitle));

        /* reversing the list according to the sort parameter received as input */
        if (action.getSortType().equals("desc")) {
            Collections.reverse(serialsList);
        }

        /* adding to the list the first n serials */
        int i = 0;
        for (Serial serial : serialsList) {
            if (serial.getDuration() != 0 && serial.filter(action) && i < action.getNumber()) {
                resultList.add(serial.getTitle());
            }
        }

        String resultMessage = "Query result: " + resultList;
        return resultMessage;
    }

    /**
     * The method that applies the most viewed query to movies
     * @param moviesData database class of movies
     * @param action the action received as input
     * @return the resulting message
     */
    public static String  moviesMostViewQuery(final UsersDataBase usersData,
                                              final MoviesDataBase moviesData,
                                              final ActionInputData action) {
        /* creating the necessary lists */
        ArrayList<String> resultList = new ArrayList<>();
        ArrayList<Movie> moviesList = moviesData.getMoviesList();

        /* for each movie calculate the sum of views */
        for (Movie movie : moviesList) {
            movie.calculateSumViews(usersData);
        }

        /* sorting the list by total number of views and by name */
        Collections.sort(moviesList, Comparator.comparing(Movie::getNumberViews)
                        .thenComparing(Movie::getTitle));

        /* reversing the list according to the sort parameter received as input */
        if (action.getSortType().equals("desc")) {
            Collections.reverse(moviesList);
        }

        /* adding to the list the first n movies */
        int i = 0;
        for (Movie movie : moviesList) {
            if (movie.getNumberViews() != 0 && movie.filter(action) && i < action.getNumber()) {
                resultList.add(movie.getTitle());
            }
        }

        String resultMessage = "Query result: " + resultList;
        return resultMessage;
    }

    /**
     * The method that applies the most viewed query to serials
     * @param serialsData database class of serials
     * @param action the action received as input
     * @return the resulting message
     */
    public static String serialsMostViewQuery(final UsersDataBase usersData,
                                              final SerialsDataBase serialsData,
                                              final ActionInputData action) {
        /* creating the necessary lists */
        ArrayList<String> resultList = new ArrayList<>();
        ArrayList<Serial> serialsList = serialsData.getSerialsList();

        /* for each movie calculate the sum of views */
        for (Serial serial: serialsList) {
            serial.calculateSumViews(usersData);
        }

        /* sorting the list by total number of views and by name */
        Collections.sort(serialsList, Comparator.comparing(Serial::getNumberViews)
                        .thenComparing(Serial::getTitle));


        /* reversing the list according to the sort parameter received as input */
        if (action.getSortType().equals("desc")) {
            Collections.reverse(serialsData.getSerialsList());
        }

        /* adding to the list the first n serials */
        int i = 0;
        for (Serial serial : serialsData.getSerialsList()) {
                if (serial.getNumberViews() != 0 && serial.filter(action)
                        && i < action.getNumber()) {
                    resultList.add(serial.getTitle());
            }
        }
        String resultMessage = "Query result: " + resultList;
        return resultMessage;
    }

    /**
     * The method that applies the num ratings query
     * @param usersData database class of users
     * @param action the action received as input
     * @return the resulting message
     */
    public static String numRatings(final UsersDataBase usersData, final ActionInputData action) {

        /* creating the necessary lists */
        ArrayList<User> usersList = usersData.getUsersList();
        ArrayList<String> resultList = new ArrayList<>();

        /* sorting the list by total number of ratings given and by name */
        Collections.sort(usersList, Comparator.comparing(User::getGivenRatingsNumber)
                        .thenComparing(User::getUsername));

        /* reversing the list according to the sort parameter received as input */
        if (action.getSortType().equals("desc")) {
            Collections.reverse(usersList);
        }

        /* adding to the list the first n users */
        int j = 0;
        for (int i = 0; i < usersList.size(); ++i) {
            if (j < action.getNumber()) {
                if (usersList.get(i).getGivenRatingsNumber() != 0) {
                    resultList.add(usersList.get(i).getUsername());
                    ++j;
                }
            }
        }

        String resultMessage = "Query result: " + resultList;
        return resultMessage;
    }

}
