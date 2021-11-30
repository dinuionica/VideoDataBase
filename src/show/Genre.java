package show;

import database.ShowsDataBase;
import database.UsersDataBase;
import user.User;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Genre {
    HashMap<String, Integer> genres = new HashMap<>();

    int factorPopularity;

    String nameGenre;

    public Genre() {
        this.genres = new HashMap<>();
    }

    public void updateInformation(String name) {
        this.genres.put(name, 0);
        this.nameGenre = name;
        this.factorPopularity = 0;
    }

    public String getNameGenre() {
        return nameGenre;
    }

    public HashMap<String, Integer> getGenres() {
        return genres;
    }

    public void setGenres(HashMap<String, Integer> genres) {
        this.genres = genres;
    }

    public int getFactorPopularity() {
        return factorPopularity;
    }

    public void calculatePopularity(ShowsDataBase showsData) {
        for (Show show : showsData.getShowsList()) {
            for (String genre : show.getGenres()) {
                if (this.genres.containsKey(genre)) {
                    this.genres.put(genre, this.genres.get(genre) + show.getNumberViews());
                    this.factorPopularity = this.genres.get(genre);
                } else {
                    this.genres.put(genre, show.getNumberViews());
                    this.factorPopularity = this.genres.get(genre);
                }
            }
        }
    }
}
