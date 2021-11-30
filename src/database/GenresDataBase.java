package database;

import show.Genre;
import java.util.ArrayList;

/**
 * The class used to create the database genres list
 */
public final class GenresDataBase {
    private ArrayList<Genre> genresList;

    /* constructor */
    public GenresDataBase(final ArrayList<Genre> genresList) {
        this.genresList = genresList;
    }

    /* getters and setters */
    public ArrayList<Genre> getGenresList() {
        return genresList;
    }

    public void setGenresList(final ArrayList<Genre> genresList) {
        this.genresList = genresList;
    }
}
