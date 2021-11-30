package database;

import show.Show;

import java.util.ArrayList;

/**
 * The class used to create the database show list
 */
public final class ShowsDataBase {
    private ArrayList<Show> showsList;

    /* constructor */
    public ShowsDataBase(final ArrayList<Show> showsList) {
        this.showsList = showsList;
    }

    /* getters and setter */
    public ArrayList<Show> getShowsList() {
        return showsList;
    }

    public void setShowsList(final ArrayList<Show> showsList) {
        this.showsList = showsList;
    }

    /**
     * The method that finds a show in database by a desire name
     * @param desiredName the name of the show
     * @return the desired show
     */
    public Show findShowDataBase(final String desiredName) {
        for (Show show : this.showsList) {
            if (show.getTitle().equals(desiredName)) {
                return show;
            }
        }
        return null;
    }
}
