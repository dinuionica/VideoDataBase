package main;

import action.Command;
import checker.Checkstyle;
import checker.Checker;
import common.Constants;
import database.ActorsDataBase;
import database.CreateDataBase;
import database.GenresDataBase;
import database.MoviesDataBase;
import database.SerialsDataBase;
import database.ShowsDataBase;
import database.UsersDataBase;
import fileio.ActionInputData;
import fileio.Input;
import fileio.InputLoader;
import fileio.Writer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * The entry point to this homework. It runs the checker that tests your implementation.
 */
public final class Main {
    /**
     * for coding style
     */
    private Main() {
    }

    /**
     * Call the main checker and the coding style checker
     *
     * @param args from command line
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(Constants.TESTS_PATH);
        Path path = Paths.get(Constants.RESULT_PATH);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        File outputDirectory = new File(Constants.RESULT_PATH);

        Checker checker = new Checker();
        checker.deleteFiles(outputDirectory.listFiles());

        for (File file : Objects.requireNonNull(directory.listFiles())) {

            String filepath = Constants.OUT_PATH + file.getName();
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getAbsolutePath(), filepath);
            }
        }

        checker.iterateFiles(Constants.RESULT_PATH, Constants.REF_PATH, Constants.TESTS_PATH);
        Checkstyle test = new Checkstyle();
        test.testCheckstyle();
    }

    /**
     * @param filePath1 for input file
     * @param filePath2 for output file
     * @throws IOException in case of exceptions to reading / writing
     */

    public static void action(final String filePath1,
                              final String filePath2) throws IOException {
        InputLoader inputLoader = new InputLoader(filePath1);
        Input input = inputLoader.readData();

        Writer fileWriter = new Writer(filePath2);
        JSONArray arrayResult = new JSONArray();
        /* create resultMessage and result object */
        String resultMessage;
        JSONObject object;

        /* Creating classes with database-specific lists */
        UsersDataBase usersData = CreateDataBase.createUsersList(input);
        ShowsDataBase showsData = CreateDataBase.createShowsList(input);
        SerialsDataBase serialsData = CreateDataBase.createSerialsList(input);
        MoviesDataBase moviesData = CreateDataBase.createMoviesList(input);
        ActorsDataBase actorsData = CreateDataBase.createActorsList(input);
        GenresDataBase genresData = CreateDataBase.createGenresList();

        /* parsing commands according to its type */
        for (ActionInputData action : input.getCommands()) {
            switch (action.getActionType()) {
                case "command" -> {
                    resultMessage = Command.parseCommand(usersData, moviesData,
                                    serialsData, action);
                    object = fileWriter.writeFile(action.getActionId(), null, resultMessage);
                    arrayResult.add(object);
                }
                default -> {
                    System.out.println("Error action type!");
                }
            }
        }
        fileWriter.closeJSON(arrayResult);
    }
}
