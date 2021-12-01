package action;

import database.MoviesDataBase;
import database.SerialsDataBase;
import database.UsersDataBase;
import fileio.ActionInputData;

import static action.Commands.favoriteCommand;
import static action.Commands.ratingCommand;
import static action.Commands.viewCommand;

public final class CommandsParsing {
    /* default constructor */
    private CommandsParsing() {

    }
    /**
     * The method that parses commands and calls the appropriate methods
     * @param usersData database class for users
     * @param moviesData database class for movies
     * @param serialsData database class for serials
     * @param action the action received as input
     * @return the resulting message
     */
    public static String parse(final UsersDataBase usersData,
                               final MoviesDataBase moviesData,
                               final SerialsDataBase serialsData,
                               final ActionInputData action) {
        return switch (action.getType()) {
            case "favorite" -> favoriteCommand(usersData, action);
            case "view" -> viewCommand(usersData, action);
            case "rating" -> ratingCommand(usersData, moviesData, serialsData, action);
            default -> null;
        };
    }
}
