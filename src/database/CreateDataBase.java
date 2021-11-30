package database;

import actor.Actor;
import fileio.ActorInputData;
import fileio.Input;
import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.UserInputData;
import show.Genre;
import show.Movie;
import show.Serial;
import show.Show;
import user.User;
import java.util.ArrayList;
import java.util.List;

/**
 * The class used to create the database
 */
public final class CreateDataBase {
    /* default constructor */
    private CreateDataBase() {

    }

    /**
     * The method that creates the class that contains the list of users
     * @param input input
     * @return the desired class
     */
    public static UsersDataBase createUsersList(final Input input) {
        ArrayList<User> usersList = new ArrayList<>();
        List<UserInputData> inputUsersList = input.getUsers();

        /* scrolling through the list from the input, creating a new user
         * with the obtained input data and adding it to the database list
         */
        for (UserInputData userInput : inputUsersList) {
            User newUser = new User(userInput);
            usersList.add(newUser);
        }
        /* return the new class with the user list */
        return new UsersDataBase(usersList);
    }

    /**
     * The method that creates the class that contains the list of movies
     * @param input input
     * @return the desired class
     */
    public static MoviesDataBase createMoviesList(final Input input) {
        ArrayList<Movie> moviesList = new ArrayList<>();
        List<MovieInputData> inputMoviesList = input.getMovies();

        /* scrolling through the list from the input, creating a new movie
         * with the obtained input data and adding it to the database list
         */
        for (MovieInputData movieInput : inputMoviesList) {
            Movie newMovie = new Movie(movieInput);
            moviesList.add(newMovie);
        }

        /* return the new class with the movies list */
        return new MoviesDataBase(moviesList);
    }

    /**
     * The method that creates the class that contains the list of serials
     * @param input input
     * @return the desired class
     */
    public static SerialsDataBase createSerialsList(final Input input) {
        ArrayList<Serial> serialList = new ArrayList<>();
        List<SerialInputData> inputSerialsList = input.getSerials();

        /* scrolling through the list from the input, creating a new serial
         * with the obtained input data and adding it to the database list
         */
        for (SerialInputData serialInput : inputSerialsList) {
            Serial newSerial = new Serial(serialInput);
            serialList.add(newSerial);
        }

        /* return the new class with the serials list */
        return new SerialsDataBase(serialList);
    }

    /**
     * The method that creates the class that contains the list of actors
     * @param input input
     * @return the desired class
     */
    public static ActorsDataBase createActorsList(final Input input) {
        ArrayList<Actor> actorsList = new ArrayList<>();
        List<ActorInputData> inputActorsList = input.getActors();

        /* scrolling through the list from the input, creating a new actor
         * with the obtained input data and adding it to the database list
         */
        for (ActorInputData inputActor : inputActorsList) {
            Actor newActor = new Actor(inputActor);
            actorsList.add(newActor);
        }

        /* return the new class with the actors list */
        return new ActorsDataBase(actorsList);
    }

    /**
     * The method that creates the class that contains the list of shows
     * @param input input
     * @return the desired class
     */
    public static ShowsDataBase createShowsList(final Input input) {
        MoviesDataBase moviesData = createMoviesList(input);
        SerialsDataBase serialsData = createSerialsList(input);
        ArrayList<Show> showsList = new ArrayList<>();

        /* we assume that movies will be entered into the database first, and then serials */
        /* adding to the video list all the movies from the movie list of the database */
        for (Movie movie : moviesData.getMoviesList()) {
            showsList.add(movie);
        }
        /* adding to the video list all the serials from the serials list of the database */
        for (Serial serial : serialsData.getSerialsList()) {
            showsList.add(serial);
        }

        /* return the new class with the actors list */
        return new ShowsDataBase(showsList);
    }

    /**
     * The method that creates the class that contains the list of genres
     * @return the desired class
     */
    public static GenresDataBase createGenresList() {
        /* Enum that contains all kinds of videos in the database */
        enum EnumGenreDataBase {
            TV_MOVIE("TV Movie"), DRAMA("Drama"), FANTASY("Fantasy"),
            COMEDY("Comedy"), FAMILY("Family"), WAR("War"),
            SCI_FI_FANTASY("Sci-Fi & Fantasy"), CRIME("Crime"),
            ANIMATION("Animation"), SCIENCE_FICTION("Science Fiction"),
            ACTION("Action"), HORROR("Horror"), MYSTERY("Mystery"),
            WESTERN("Western"), ADVENTURE("Adventure"),
            ACTION_ADVENTURE("Action & Adventure"), ROMANCE("Romance"),
            THRILLER("Thriller"), KIDS("Kids"), HISTORY("History");

            private final String content;
            /* setters and getters for enum */
            EnumGenreDataBase(String d) {
                this.content = d;
            }
            public String getContent() {
                return content;
            }
        }
        ArrayList<Genre> genresList = new ArrayList<>();

        /* For each type of genre, create a new object, update
         * the information and add it to the desired list
         */
        for (EnumGenreDataBase genre : EnumGenreDataBase.values()) {
            Genre newGenre = new Genre();
            newGenre.updateInformation(genre.getContent());
            genresList.add(newGenre);
        }

        /* return the new class with the genres list */
        return new GenresDataBase(genresList);
    }
}
