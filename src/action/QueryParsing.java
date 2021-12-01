package action;

import database.ActorsDataBase;
import database.MoviesDataBase;
import database.SerialsDataBase;
import database.ShowsDataBase;
import database.UsersDataBase;
import fileio.ActionInputData;
import static action.Query.averageQuery;
import static action.Query.awardsQuery;
import static action.Query.filterDescriptionQuery;
import static action.Query.moviesFavoriteQuery;
import static action.Query.moviesLongestQuery;
import static action.Query.moviesMostViewQuery;
import static action.Query.moviesRatingQuery;
import static action.Query.numRatings;
import static action.Query.serialsFavoriteQuery;
import static action.Query.serialsLongestQuery;
import static action.Query.serialsMostViewQuery;
import static action.Query.serialsRatingQuery;


public final class QueryParsing {

    /* default constructor */
    private QueryParsing() {

    }

    /**
     * The method that parses queries and calls the appropriate methods
     * @param usersData database class for users
     * @param showsData database class for shows
     * @param moviesData database class for movies
     * @param serialsData database class for serials
     * @param actorsData database class for actors
     * @param action the action received as input
     * @return the resulting message
     */
    public static String parse(final UsersDataBase usersData,
                               final ShowsDataBase showsData,
                               final MoviesDataBase moviesData,
                               final SerialsDataBase serialsData,
                               final ActorsDataBase actorsData,
                               final ActionInputData action) {

        switch (action.getCriteria()) {
            /* case actors */
            case "average" -> {
                return averageQuery(usersData, actorsData, moviesData, serialsData, action);
            }
            case "awards" -> {
                return awardsQuery(usersData, actorsData, action);
            }
            case "filter_description" -> {
                return filterDescriptionQuery(actorsData, action);
                /* case users */
            }
            case "longest" -> {
                if (action.getObjectType().equals("movies")) {
                    return moviesLongestQuery(moviesData, action);
                } else if (action.getObjectType().equals("shows")) {
                    return serialsLongestQuery(serialsData, action);
                }
            }
            case "most_viewed" -> {
                if (action.getObjectType().equals("movies")) {
                    return moviesMostViewQuery(usersData, moviesData, action);
                } else if (action.getObjectType().equals("shows")) {
                    return serialsMostViewQuery(usersData, serialsData, action);
                }
            }
            case "ratings" -> {
                if (action.getObjectType().equals("movies")) {
                    return moviesRatingQuery(moviesData, action);
                } else if (action.getObjectType().equals("shows")) {
                    return serialsRatingQuery(serialsData, action);
                }
            }
            case "favorite" -> {
                if (action.getObjectType().equals("movies")) {
                    return moviesFavoriteQuery(usersData, moviesData, action);
                } else if (action.getObjectType().equals("shows")) {
                    return serialsFavoriteQuery(usersData, serialsData, action);
                }
                /* case users */
            }
            case "num_ratings" -> {
                return numRatings(usersData, action);
            }
            default -> {
                return "Error action type!";
            }
        }
        return null;
    }
}
