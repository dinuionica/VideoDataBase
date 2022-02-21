


# Video DataBase 
------------------------------------------------------------------------------- 

This project simulates a simplified platform that provides information about movies and serials.
The platform performs actions that users can take on a movie viewing platform: ratings, movie
viewing, searches, recommendations. Users can receive personalized recommendations based on their preferences.
The project was written in the Java language, using the concepts of Object Oriented Programming. <br>
The data read from the test file is loaded as input, in JSON format, into objects. Then it executes 
sequential commands, queries or recommendations that have an effect on the repository. The final 
results, after executing an action, are loaded into the resulting JSON file.

------------------------------------------------------------------------------- 

## Class Structure And Specific Relations

To solve the topic, a series of classes and packages have been implemented: <br>
* SHOW <br>
     * Show   -> abstract class specific to a Video implements abstract methods <br>
     * Movie  -> class that inherits the Show class, specific to a movie <br>
     * Serial -> class that inherits the Show class, specific to a Serial <br>
     * Genres -> class specific to a particular genre type of a video <br>

* USER <br>
     * User   -> class specific to a user <br>

* ACTOR <br>
     * Actor -> class specific to an actor, based on this class, queries are made for actors <br>

    
* DATABASE  <br>
     * UserDataBase -> class contains a list of User objects <br>
     * ShowsDataBase -> class contains a list of Shows objects <br>
     * SerialsDataBase -> class contains a list of Serial objects <br>
     * MoviesDataBase -> class contains a list of Movie objects <br>
     * ActorsDataBase -> class contains a list of Actor objects <br>
     * GenresDataBase -> class contains a list of Genre objects <br> 
     * CreateDataBase -> class in which the above classes are created <br>
        mentioned, with the lists specific to each <br>

* ACTION 
    -> CommandsParsing -> the class that deals with the interpretation of each
          commands and calling the appropriate methods

   -> QueryParsing -> the class that deals with the interpretation of each
      query type and calling the appropriate methods

   -> RecommendationParsing -> interpretation class
     each recommendation and which uses the appropriate methods

   -> Commands -> the class that contains the three methods (favoriteCommand,
    viewCommand, ratingCommand) which applies specific commands

   -> Query -> class containing query methods (awardsQuery,
   serialsFavoriteQuery etc), which applies specific query type

   -> Recommendation -> the class that contains the recommendation methods
   (standardRecommendation, favoriteRecommendation etc) that applies the type
   specific recommendation
-------------------------------------------------------------------------------
LOGIC

The main method creates the specific classes for the database,
thus taking over the input of the program. Based on the type of action, it is parsed
each action and the method of the corresponding class is called.

-------------------------------------------------- ----------------------------
COMMANDS:

The order type is interpreted, and the type-specific method is called.
Each method returns a string, which is the result of what will be written in
the resulting JSON object,

favoriteCommand -> check if a user has watched a video, if any
already present in the favorites list, and if not add the movie to the list
favorites of the desired user.

viewCommand -> check if a user has watched a video, and update
the number of views depending on the case

ratingCommand -> similarly check if the video has been viewed,
and then add a rating to a video based on its type (movie or serial)
If the user has already rated the video, a message is displayed
suitable.
-> The rating given by a user to a movie, I chose to implement it
in the form of HashMap because it is unique, and its value is needed.

-------------------------------------------------- ----------------------------
QUERY:

Similarly, the type of order is interpreted based on the transmitted action
as input. If the transmitted object type is movies, it is
calls movies specific methods, and in case of contract calls methods
specific serials.
Each method returns a string, which is the result of what will be written in
the resulting JSON object,

awargeQuery -> calculates for each actor the average rating of the video
the lists in which he played, the lists are sorted, and finally added to the list
of results the first actors in actors.

awardsQuery -> this method calculates the number of prizes for
Each actor sorts the lists, and then adds them to the final list
results actors who contain all the necessary awards.

filterQuery -> this method sorts the list of actors by name,
and all the actors in the description are added to the final list of strings
the series of keywords transmitted as input through the action.

moviesRatingQuery -> sort movie list by average rating and name,
and the first n movies are added to the final list that meets the filter criteria.

moviesFavoriteQuery -> calculates the number of devices for each movie
favorites, the list is sorted and the first results are added to the final list of results
in movies that meet the filter criteria.

moviesLongestQuery -> sorts the movie list by length and name, and then
the first in films that meet the criteria is added to the final list of results
filter and having a viewing time other than 0

moviesMosViewQuert -> calculates the total number of movies for each movie
views, sort movie lists, and finally add them to the playlist
results first in movies that meet the criteria of filters and have the number
all views other than 0

Similarly, serial-specific methods are implemented, only
the reasoning applies to a list of series.


-------------------------------------------------- ----------------------------
RECOMMENDATION:

Interpret the type of recommendation and use the appropriate methods.
Each method returns a string, which is the result of what will be written in
the resulting JSON object,

StandardRecommendation -> scroll through the list of videos and return
the first video not in the user's viewing history

bestUnseenRecommendation -> sort video list by rating
average and order in the database, the list is reversed and returned first
unseen video

popularRecommendation -> check the user type, then for
each genre the popularity factor. Then sort the lists and search in
the user database to which the recommendation applies. In the end it goes through
every genre, then every video and if one finds the most popular video
genre that has not already been viewed by the user, is returned as
recommendation.
-> for the popularity of a genre I chose to implement a Genre class, which
contains a HashMap that contains each genre and the popularity factor of a
his. To create the genre list in the database we implemented a
enum (EnumGenreDataBase) which contains all types of genres, and depending on
creating the database I added each genre in the list of genres.

favoriteRecommendation -> check the user type, then for each
video calculates the number of appearances in the favorite lists. Looking for
the user to whom the recommendation applies, sorts the list by the number of
appearances, and then returns the first video that was not viewed by
user.

searchRecommendation -> similarly check user type and
its existence in the database, the list of videos is sorted by rating
and name, and then search for the first video not viewed by the user who
it is in the desired genre and is returned. If such a thing cannot be done
the appropriate type of message returns an appropriate message.

To make the sorts I used Collections.sort with which
sorting can be implemented based on several comparators.
Similarly, I reversed the lists.
