package database;

import actor.Actor;
import java.util.ArrayList;

/**
 * The class used to create the database users list
 */
public final class ActorsDataBase {
    private ArrayList<Actor> actorsList;

    /* constructor */
    public ActorsDataBase(final ArrayList<Actor> actorList) {
        this.actorsList = actorList;
    }

    /* getters and setters */
    public ArrayList<Actor> getActorList() {
        return actorsList;
    }

    public void setActorList(final ArrayList<Actor> actorList) {
        this.actorsList = actorList;
    }

    /**
     * The method that finds an actor in database by a desire name
     * @param desiredName the name of the actor
     * @return the desired actor
     */
    public Actor findActorDataBase(final String desiredName) {
        for (Actor actor: this.actorsList) {
            if (actor.getName().equals(desiredName)) {
                return actor;
            }
        }
        return null;
    }
}
