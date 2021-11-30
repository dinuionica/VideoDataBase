package user;

import fileio.UserInputData;

import java.util.ArrayList;
import java.util.Map;

public final class User {
    /**
     * User's username
     */
    private final String username;
    /**
     * Subscription Type
     */
    private final String subscriptionType;
    /**
     * The history of the movies seen
     */
    private final Map<String, Integer> history;
    /**
     * Movies added to favorites
     */
    private final ArrayList<String> favoriteMovies;

    private int givenRatingsNumber;

    /* constructor */
    public User(final String username, final String subscriptionType,
                final Map<String, Integer> history,
                final ArrayList<String> favoriteMovies) {
        this.username = username;
        this.subscriptionType = subscriptionType;
        this.history = history;
        this.favoriteMovies = favoriteMovies;
        this.givenRatingsNumber = 0;
    }

    public User(final UserInputData userInput) {
        this.username = userInput.getUsername();
        this.favoriteMovies = userInput.getFavoriteMovies();
        this.history = userInput.getHistory();
        this.subscriptionType = userInput.getSubscriptionType();
        this.givenRatingsNumber = 0;
    }

    /* getters */
    public String getUsername() {
        return username;
    }

    public Map<String, Integer> getHistory() {
        return history;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public ArrayList<String> getFavoriteMovies() {
        return favoriteMovies;
    }

    public int getGivenRatingsNumber() {
        return givenRatingsNumber;
    }

    /**
     * Function
     */
    public void incrementGivenRatingNumber() {
        this.givenRatingsNumber++;
    }

    @Override
    public String toString() {
        return "UserInputData{" + "username='"
                + username + '\'' + ", subscriptionType='"
                + subscriptionType + '\'' + ", history="
                + history + ", favoriteMovies="
                + favoriteMovies + '}';
    }
}
