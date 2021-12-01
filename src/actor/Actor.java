package actor;

import database.MoviesDataBase;
import database.SerialsDataBase;
import fileio.ActionInputData;
import fileio.ActorInputData;
import show.Movie;
import show.Serial;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * Class of an actor
 */
public final class Actor {
    /**
     * Actor name
     */
    private String name;
    /**
     * Description of the actor's career
     */
    private final String careerDescription;
    /**
     * Videos starring actor
     */
    private ArrayList<String> filmography;
    /**
     * Awards won by the actor
     */
    private final Map<ActorsAwards, Integer> awards;

    /**
     * Average rating of shows
     */
    private double averageRating;
    /**
     * Number of awards of an cactor
     */
    private int numberAwards;

    /* constructor */
    public Actor(final String name, final String careerDescription,
                 final ArrayList<String> filmography, final Map<ActorsAwards, Integer> awards) {
        this.name = name;
        this.careerDescription = careerDescription;
        this.filmography = filmography;
        this.awards = awards;
        this.averageRating = 0;
    }

    /* the constructor that receives as argument an actor ActorInputData type */
    public Actor(final ActorInputData actor) {
        this.name = actor.getName();
        this.careerDescription = actor.getCareerDescription();
        this.filmography = actor.getFilmography();
        this.awards = actor.getAwards();
        this.averageRating = 0;
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

    /**
     * The method that calculates the average rating
     * @return the average rating
     */
    public double getAverageRating() {
        return averageRating;
    }

    /**
     * The method that calculates the number of awards
     * @return the number of awards
     */
    public int getNumberAwards() {
        return numberAwards;
    }

    /**
     * The method that calculates the average rating of actor's shows
     * @param moviesDataBase database class for movies
     * @param serialsData database class for serials
     */
    public void calculateRatingActor(final MoviesDataBase moviesDataBase,
                                     final SerialsDataBase serialsData) {
        ArrayList<Movie> moviesList = moviesDataBase.getMoviesList();
        ArrayList<Serial> serials = serialsData.getSerialsList();
        double sumRating = 0;
        double numberRating = 0;

        for (String movieName : filmography) {
            for (Movie movie : moviesList) {
                /* add to the current sum the average rating of each movie */
                if (movie.getTitle().equals(movieName) && movie.getAverageRating() != 0) {
                        sumRating += movie.getAverageRating();
                        numberRating++;
                }
            }
            for (Serial serial : serials) {
                /* add to the current sum the average rating of each serial */
                if (serial.getTitle().equals(movieName) && serial.getAverageRating() != 0) {
                        sumRating += serial.getAverageRating();
                        numberRating++;
                }
            }
        }
        /* calculate the final average rating */
        if (numberRating != 0) {
            this.averageRating = (sumRating) / (numberRating);
        }
    }

    /**
     * The method that calculates the number of awards received by an actor
     */
    public void calculateNumberAwards() {
        for (Map.Entry<ActorsAwards, Integer> award : this.awards.entrySet()) {
            if (award.getValue() != 0) {
                this.numberAwards += award.getValue();
            }
        }
    }

    /**
     *  The method that checks whether the elements in the description of an
     *  actor correspond to all the elements read from the input
     * @param action the action received as input
     * @return true/false
     */
    public boolean checkDescriptionActor(final ActionInputData action) {
        List<String> description = Arrays.asList(this.getCareerDescription()
                                                 .toLowerCase().split("[ ,.-]"));
        for (String word : action.getFilters().get(2)) {
            if (!description.contains(word)) {
                return false;
            }
        }
        return true;
    }

    /**
     *  The method that checks if an actor has received all the
     *  awards mentioned in the input list received
     * @param action the action received as input
     * @return true/false
     */
    public boolean checkAllAwardsActor(final ActionInputData action) {

        /* getting the filter list */
        List<List<String>> filters = action.getFilters();
        final int numberListFilters = 3;
        List<String> awardList = filters.get(numberListFilters);
        int numberAwardsActor = 0;
        ArrayList<String> listNameAwards = new ArrayList<>();

        /* getting the list with the names of the awards received by the actor */
        for (ActorsAwards awardsName : this.getAwards().keySet()) {
            listNameAwards.add(awardsName.toString());
        }

        for (String awardName : listNameAwards) {
            for (String awardNameInput: awardList) {
                /* if the prizes are equal, the number of prizes increases */
                if (awardName.equals(awardNameInput)) {
                    numberAwardsActor++;
                }
            }
        }

        /* if the number of prizes obtained is equal to the number of prizes
         * in the list received as input, a true answer is returned.
         */
        if (numberAwardsActor != awardList.size()) {
            return false;
        }
        return true;
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
