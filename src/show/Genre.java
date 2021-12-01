package show;

import database.ShowsDataBase;
import java.util.HashMap;

/**
 * Class of a Genre
 */
public class Genre {

    /**
     * Gender type with popularity factor
     */
    private HashMap<String, Integer> genres = new HashMap<>();

    /**
     * Popularity factor
     */
    private int popularityFactor;

    /**
     * Name of a genre
     */
    private String nameGenre;

    /* constructor */
    public Genre() {
        this.genres = new HashMap<>();
    }

    /**
     * The method that returns the name of genre
     * @return the desired name
     */
    public String getNameGenre() {
        return nameGenre;
    }

    /**
     * The method that return the hashmap of genres
     * @return the desired hashmap
     */
    public HashMap<String, Integer> getGenres() {
        return genres;
    }

    /**
     * The method that sets the hashmap of genres
     * @param genres input genres
     */
    public void setGenres(final HashMap<String, Integer> genres) {
        this.genres = genres;
    }

    /**
     * The method returns the popularity factor of an actor
     * @return the factor
     */
    public int getPopularityFactor() {
        return popularityFactor;
    }

    /**
     * The method tha update the information about a genre
     * @param name the name of genre
     */
    public void updateInformation(final String name) {
        this.genres.put(name, 0);
        this.nameGenre = name;
        this.popularityFactor = 0;
    }

    /**
     * The method that calculates the popularity factor of a genre
     * @param showsData database class of shows
     */
    public void calculatePopularityFactor(final ShowsDataBase showsData) {
        for (Show show : showsData.getShowsList()) {
            int numberViews = show.getNumberViews();
            for (String genre : show.getGenres()) {
                /* if a video is in the genre list, it updates the number of views */
                if (!this.genres.containsKey(genre)) {
                    this.genres.put(genre, show.getNumberViews());
                } else {
                    this.genres.put(genre, this.genres.get(genre) + numberViews);
                }
                /* popularity factor update */
                this.popularityFactor = this.genres.get(genre);
            }
        }
    }
}
