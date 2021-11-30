package actor;

import database.MoviesDataBase;
import database.SerialsDataBase;
import fileio.ActionInputData;
import fileio.ActorInputData;
import show.Movie;
import show.Serial;


import java.util.*;

public final class Actor {
    /**
     * actor name
     */
    private String name;
    /**
     * description of the actor's career
     */
    private String careerDescription;
    /**
     * videos starring actor
     */
    private ArrayList<String> filmography;
    /**
     * awards won by the actor
     */
    private final Map<ActorsAwards, Integer> awards;

    private double ratingOfShows;

    int numberAwards;

    /* constructor */
    public Actor(String name, String careerDescription,
                 ArrayList<String> filmography,
                 Map<ActorsAwards, Integer> awards) {
        this.name = name;
        this.careerDescription = careerDescription;
        this.filmography = filmography;
        this.awards = awards;
        this.ratingOfShows = 0;
    }

    public Actor(ActorInputData actor) {
        this.name = actor.getName();
        this.careerDescription = actor.getCareerDescription();
        this.filmography = actor.getFilmography();
        this.awards = actor.getAwards();
        this.ratingOfShows = 0;
    }

    /* getters and setters */
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public ArrayList<String> getFilmography() {
        return filmography;
    }

    public void setFilmography(final ArrayList<String> filmography) {
        this.filmography = filmography;
    }

    public Map<ActorsAwards, Integer> getAwards() {
        return awards;
    }

    public String getCareerDescription() {
        return careerDescription;
    }

    public void setCareerDescription(final String careerDescription) {
        this.careerDescription = careerDescription;
    }

    public void calculateRatingActor(MoviesDataBase moviesDataBase, SerialsDataBase serialsData) {
        double sumRating = 0;
        double numberRating = 0;

        for (String movieName : filmography) {
            for (Movie movie : moviesDataBase.getMoviesList()) {
                if (movie.getTitle().equals(movieName) && movie.getAverageRatings() != 0) {
                        sumRating += movie.getAverageRatings();
                        numberRating++;
                }
            }
            for (Serial serial : serialsData.getSerialsList()) {
                if (serial.getTitle().equals(movieName) && serial.getAverageRatings() != 0) {
                        sumRating += serial.getAverageRatings();
                        numberRating++;
                }
            }

        }
        if (numberRating != 0) {
            this.ratingOfShows = (sumRating) / (numberRating);
        }
    }

    public double getRatingOfShows() {
        return ratingOfShows;
    }

    public int getNumberAwards() {
        return numberAwards;
    }

    public boolean findActor(ActionInputData action) {
        List<String> description = Arrays.asList(this.getCareerDescription().toLowerCase().split("[ ,.-]"));
        for (String word : action.getFilters().get(2)) {
            if (!description.contains(word)) {
                return false;
            }
        }
        return true;
    }

    public boolean findAllAwards(ActionInputData action) {
        List<String> awardsList = action.getFilters().get(3);
        for (String awardName : awardsList) {
            if (!this.getAwards().containsKey(awardName)) {
                return false;
            }
        }
        return true;
    }
    public void calculateNumberAwards() {
        for (Map.Entry<ActorsAwards, Integer> award : this.awards.entrySet()) {
            if (award.getValue() != 0) {
                this.numberAwards++;
            }
        }
    }
    @Override
    public String toString() {
        return "ActorInputData{"
                + "name='" + name + '\''
                + ", careerDescription='"
                + careerDescription + '\''
                + ", filmography=" + filmography + '}';
    }
}
