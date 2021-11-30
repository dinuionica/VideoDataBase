package database;

import show.Serial;
import java.util.ArrayList;

/**
 * The class used to create the database serials list
 */
public final class SerialsDataBase {
    private ArrayList<Serial> serialsList;

    /* constructor */
    public SerialsDataBase(final ArrayList<Serial> serialsList) {
        this.serialsList = serialsList;
    }

    /* getters and setters */
    public ArrayList<Serial> getSerialsList() {
        return serialsList;
    }

    public void setSerialsList(final ArrayList<Serial> serialsList) {
        this.serialsList = serialsList;
    }

    /**
     * The method that finds a serial in database by a desire name
     * @param desiredName the name of the serial
     * @return the desired serial
     */
    public Serial findSerialDataBase(final String desiredName) {
        for (Serial serial : this.serialsList) {
            if (serial.getTitle().equals(desiredName)) {
                return serial;
            }
        }
        return null;
    }
}
