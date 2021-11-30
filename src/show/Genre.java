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
    HashMap<String, Integer> genres = new HashMap<>();

    /**
     * Popularity factor
     */
    int popularityFactor;

    /**
     * Name of a genre
     */
    String nameGenre;

    /* constructor */
    public Genre() {
        this.genres = new HashMap<>();
    }

    /* getters and setters */
    public String getNameGenre() {
        return nameGenre;
    }

    public HashMap<String, Integer> getGenres() {
        return genres;
    }

    public void setGenres(HashMap<String, Integer> genres) {
        this.genres = genres;
    }

    public int getPopularityFactor() {
        return popularityFactor;
    }

    /**
     * The method tha update the information about a genre
     * @param name
     */
    public void updateInformation(String name) {
        this.genres.put(name, 0);
        this.nameGenre = name;
        this.popularityFactor = 0;
    }

    /**
     * The method that calculates the popularity factor of a genre
     * @param showsData
     */
    public void calculatePopularityFactor(ShowsDataBase showsData) {
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
