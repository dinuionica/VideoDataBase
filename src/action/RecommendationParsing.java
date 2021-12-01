package action;

import database.GenresDataBase;
import database.MoviesDataBase;
import database.SerialsDataBase;
import database.ShowsDataBase;
import database.UsersDataBase;
import fileio.ActionInputData;

import static action.Recommendation.bestUnseenRecommendation;
import static action.Recommendation.favoriteRecommendation;
import static action.Recommendation.popular;
import static action.Recommendation.searchRecommend;
import static action.Recommendation.standardRecommendation;

public final class RecommendationParsing {

    /* default constructor */
    private RecommendationParsing() {

    }
    /**
     * The method that parses recommendation and calls the appropriate methods
     * @param usersData database class for users
     * @param moviesData database class for movies
     * @param serialsData database class for serials
     * @param action the action received as input
     * @return the resulting message
     */
    public static String parseRecommend(final UsersDataBase usersData,
                                        final ShowsDataBase showsData,
                                        final MoviesDataBase moviesData,
                                        final SerialsDataBase serialsData,
                                        final GenresDataBase genresData,
                                        final ActionInputData action) {

        switch (action.getType()) {
            case "standard" -> {
                if (standardRecommendation(usersData, showsData, action) == null) {
                    return "StandardRecommendation cannot be applied!";
                }
                return standardRecommendation(usersData, showsData, action);
            }
            case "best_unseen" -> {
                if (bestUnseenRecommendation(usersData, showsData, action) == null) {
                    return "BestRatedUnseenRecommendation cannot be applied!";
                }
                return bestUnseenRecommendation(usersData, showsData, action);
            }
            case "popular" -> {
                if (popular(usersData, showsData, genresData, action) == null) {
                    return "PopularRecommendation cannot be applied!";
                }
                return popular(usersData, showsData, genresData, action);
            }
            case "favorite" ->  {
                if (favoriteRecommendation(usersData, showsData, action) == null) {
                    return "FavoriteRecommendation cannot be applied!";
                }
                return favoriteRecommendation(usersData, showsData, action);
            }
            case "search" -> {
                if (searchRecommend(usersData, showsData, action) == null) {
                    return "SearchRecommendation cannot be applied!";
                }
                return searchRecommend(usersData, showsData, action);
            }
            default -> {
                return null;
            }
        }
    }
}
