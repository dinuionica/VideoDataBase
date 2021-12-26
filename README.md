


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

To solve the topic, a series of classes and packages have been implemented:

show -> Show -> abstract class specific to a Video
             -> implements abstract methods such as getDuration which
                applies to both Movie and Serial classes

     -> Movie -> class that inherits the Show class, specific to a movie
     -> Serial -> class that inherits the Show class, specific to a Serial
     -> Genres -> class specific to a particular genre type of a video

user -> User -> class specific to a user

actor -> Actor -> class specific to an actor
               -> based on this class, queries are made for actors

    
database -> UserDataBase -> class contains a list of User objects
         -> ShowsDataBase -> class contains a list of Shows objects
         -> SerialsDataBase -> class contains a list of Serial objects
         -> MoviesDataBase -> the class contains a list of Movie objects
         -> ActorsDataBase -> class contains a list of Actor objects
         -> GenresDataBase -> the class contains a list of Genre objects
         -> CreateDataBase -> the class in which the above classes are created
            mentioned, with the lists specific to each

action -> CommandsParsing -> the class that deals with the interpretation of each
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
LOGICA 

In cadrul metodei main se creeaza clasele specifice pentru baza de date, 
preluandu-se astfel input-ul programului. Pe baza tipului actiunii, se parseaza
fiecare actiune si se apeleaza metoda din clasa corespunzatoare.

------------------------------------------------------------------------------- 
COMMANDS : 

Se interpreaza tipul comenzii, si se apeleaza metoda specifica tipului.
Fiecare metoda returneaza un string, care este rezultat ce va fi scris in 
obiectul JSON rezultat,

favoriteCommand -> verifica daca un utilizator a vizionat un video, daca il are
deja prezent in lista de favorire, iar in caz negativ adauga filmul la lista
de favorite a utilizatorului dorit.

viewCommand -> verifica daca un utilizator a vizionat un video, si actualizeaza
numarul de vizualizari in functie de caz

ratingCommand -> in mod similar se verifica daca video a fost vizualizat,
iar apoi adauga rating unui video in functie de tipul acestuia(film sau serial)
Daca utiliatorul a accordat deja un rating video-ului, se afiseaza un mesaj
corespunzator.
-> Rating-ul acordat de un utilizator unui film, am ales sa il implementez
sub forma de HashMap deoarece este unic, si este nevoie de valoarea acestuia.

------------------------------------------------------------------------------- 
QUERY : 

In mod similar, se interpreteaza tipul comenzii pe baza actiunii transmise
ca input. In cazul in care tipul obiectului transmis este de tip movies, se 
apeleaza metodele specifice movies, iar in caz contrat se apeleaza metodele
specifice serials.
Fiecare metoda returneaza un string, care este rezultat ce va fi scris in 
obiectul JSON rezultat,


awargeQuery -> se calculeaza pentru fiecare actor rating-ul mediua al video
-urilor in care a jucat, se sorteaza listele, si in final se adauga in lista 
de rezultate actorii primii n actori.

awardsQuery -> in cadrul acestei metode se calculeaza numarul de premii pentru
fiecare actor, se sorteaza listele, si apoi se adauga in lista finala de
rezultate actorii care contin toate premiile necesare.

filterQuery -> in cadrul acestei metode se sorteaza lista de actori dupa nume,
si se adauga in lista finala de string-uri toti actorii care au in descriere
seria de cuvinte cheie transmisa ca input prin intermediul actiunii.


moviesRatingQuery -> se sorteaza lista de filme dupa rating-ul mediu si nume,
si se adauga primele n filme in lista finala care respecta criteriile de filtru.

moviesFavoriteQuery -> se calculeaza pentru fiecare film numarul de aparatii 
favorite, se sorteaza lista si se adauga in lista finala de rezultate primele
n filme care respecta criteriile de filtru.

moviesLongestQuery -> se sorteaza lista de filme dupa durata si nume, iar apoi
se adauga in lista finala de rezultate primele n filme care respecta criteriile
de filtru si care au durata de vizualizare diferita de 0

moviesMosViewQuert -> se calculeaza pentru fiecare film numarul total de 
vizualizari, se sorteaza liste de filme, iar la final se adauga in lista de
rezultate primele n filme care respecta criteriile de filtre si au numarul
totat de vizualizari diferit de 0

In mod similar sunt implementate si metodele specifice serialelor, doar ca
rationamentul se aplica pe un lista de seriale.


------------------------------------------------------------------------------- 
RECOMMENDATION :

Se interpreteaza tipul recomandarii si se apeleza metodele corespunzatoare.
Fiecare metoda returneaza un string, care este rezultat ce va fi scris in 
obiectul JSON rezultat,

StandardRecommendation -> se parcurge lista de video-uri si se returneza
primul video care nu se afla in istoricul de vizionari al utilizatorului

bestUnseenRecommendation -> se sorteaza lista de video-uri dupa rating-ul
mediu si ordinea din baza de date, se inverseaza lista si se returneaza primul
video nevizualizat

popularRecommendation -> se verifica tipul utilizatorului, apoi se pentru 
fiecare gen factorul de popularitate. Apoi se sorteaza listele si se cauta in
baza de date utilizatorul pe care se aplica recomndarea. In final se parcurge
fiecare gen, apoi fiecare video si daca se gaseste un video din cel mai popular
gen care nu a fost deja vizualizat de utilizator, se returneaza ca si 
recomandare. 
-> pentru popularitate unui gen am ales sa implementez o clasa Genre, care
contine un HashMap care contine fiecare gen si factorul de popularite al a
acestuia. Pentru create listei de genuri din baza de date am implementat un 
enum (EnumGenreDataBase) care contine toate tipurile de gen, iar in functia de
creare a bazei de date am adaugat pe rand fiecare gen in lista de genuri.

favoriteRecommendation -> se verifica tipul utilizatorului, apoi pentru fiecare
video se calculeaza numarul de aparitii in listele de favorite. Se cauta 
utilizatorul pe care se aplica recomndarea, se sorteaza lista dupa numarul de
aparitii, iar apoi se returneaza primul video care nu a fost vizualizat de 
utilizator.

searchRecommendation -> in mod similar se verifica tipul utilizatorului si
existenta acestuia in baza de date, se sorteaza lista de videouri dupa rating
si nume, iar apoi se cauta primul video nevizualizat de utilizator care 
se afla in genul dorit si se returneaza. Daca nu se poate realiza un astfel
de tip de recomandare se intoarce un mesaj corespunzator.

Pentru realizarea sortarilor am folosit Collections.sort cu ajutorul caruia
se poate implementa o sortare pe baza mai multor comparatori.
In mod similar am realizat si inversarea listelor.
