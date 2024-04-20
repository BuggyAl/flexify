package me.buggyal.hshacks2024;

import me.buggyal.hshacks2024.app.App;
import me.buggyal.hshacks2024.enums.WorkoutDifficulty;
import me.buggyal.hshacks2024.enums.WorkoutTarget;
import me.buggyal.hshacks2024.enums.WorkoutType;
import org.json.JSONArray;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static JSONArray exerciseArray;

    public static Workout workout;

    public static void main(String[] args) {
        try {
            parseExercises();
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }

        workout = new Workout(WorkoutDifficulty.BEGINNER, WorkoutType.STRENGTH, WorkoutTarget.ARMS);
        workout.generateWorkout();
        System.out.println(workout.getWorkout());

        App.runApp();

    }

    public static void parseExercises() throws URISyntaxException, IOException {
        URL url = Main.class.getResource("/exercises.json");
        if (url == null) throw new RuntimeException("Could not find exercises.json");
        Path path = Paths.get(url.toURI());
        String jsonContent = Files.readString(path);
        JSONArray exerciseArray = new JSONArray(jsonContent);
        Main.exerciseArray = exerciseArray;
    }



}
