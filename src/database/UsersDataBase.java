package database;

import user.User;
import java.util.ArrayList;

/**
 * The class used to create the database users list
 */
public final class UsersDataBase {
    private ArrayList<User> usersList;

    /* constructor */
    public UsersDataBase(final ArrayList<User> usersList) {
        this.usersList = usersList;
    }

    /* getters and setter */
    public ArrayList<User> getUsersList() {
        return usersList;
    }

    public void setUsersList(final ArrayList<User> usersList) {
        this.usersList = usersList;
    }

    /**
     * The method that finds a user in database by a desire name
     * @param desiredName the name of the user
     * @return the desired user
     */
    public User findUserDataBase(final String desiredName) {
        for (User user: this.usersList) {
            if (user.getUsername().equals(desiredName)) {
                return user;
            }
        }
        return null;
    }
}
