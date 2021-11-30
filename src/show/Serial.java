package show;

import entertainment.Season;
import fileio.SerialInputData;
import java.util.ArrayList;

/**
 * Class of a serial
 */
public final class Serial extends Show {
    /**
     * Number of seasons
     */
    private final int numberOfSeasons;
    /**
     * Season list
     */
    private final ArrayList<Season> seasons;

    /* constructor */
    public Serial(final String title, final int year, final ArrayList<String> cast,
                  final ArrayList<String> genres, final int numberOfSeasons,
                  final ArrayList<Season> seasons) {
        super(title, year, cast, genres);
        this.numberOfSeasons = numberOfSeasons;
        this.seasons = seasons;

    }

    /* the constructor that receives as argument a serial SerialInputData type */
    public Serial(final SerialInputData serial) {
        super(serial.getTitle(), serial.getYear(), serial.getCast(), serial.getGenres());
        this.numberOfSeasons = serial.getNumberSeason();
        this.seasons = serial.getSeasons();

    }

    /* getters */
    public int getNumberSeason() {
        return numberOfSeasons;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    /**
     * The method that calculates the average rating of a serial
     * @return the rating
     */
    public double getAverageRating() {
        /* add to the current sum the rating of each season */
        double sumRating = 0;
        for (Season season : this.seasons) {
            sumRating += season.getRatingSeason();
        }
        return sumRating / numberOfSeasons;
    }

    /**
     * The method that calculates the duration of a serial
     * @return the dureation
     */
    @Override
    public int getDuration() {
        /* add to the current sum the duration of each season */
        int sumDuration = 0;
        for (Season season : this.getSeasons()) {
            sumDuration += season.getDuration();
        }
        return sumDuration;
    }

    @Override
    public String toString() {
        return "SerialInputData{" + " title= "
                + super.getTitle() + " " + " year= "
                + super.getYear() + " cast {"
                + super.getCast() + " }\n" + " genres {"
                + super.getGenres() + " }\n "
                + " numberSeason= " + numberOfSeasons
                + ", seasons=" + seasons + "\n\n" + '}';
    }
}
