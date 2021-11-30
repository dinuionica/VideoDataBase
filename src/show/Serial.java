package show;

import entertainment.Season;
import fileio.SerialInputData;
import java.util.ArrayList;

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
     * function
     * @return
     */
    public double getAverageRatings() {
        double sumRatings = 0;
        for (Season season : this.seasons) {
            sumRatings += season.getRatingSeason();
        }
        return sumRatings / numberOfSeasons;
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

    @Override
    public int getDuration() {
        int sumDuration = 0;
        for (Season season : this.getSeasons()) {
            sumDuration += season.getDuration();
        }
        return sumDuration;
    }
}
