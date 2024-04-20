package me.buggyal;

import org.json.JSONArray;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static JSONArray exerciseArray;

    public static void main(String[] args) {
        try {
            parseExercises();
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }

        List<String> muscleList = new ArrayList<>();
        for (int i = 0; i < exerciseArray.length(); i++) {
            JSONArray primary = exerciseArray.getJSONObject(i).getJSONArray("primaryMuscles");
            for (int j = 0; j < primary.length(); j++) {
                String muscle = primary.getString(j);
                if (!muscleList.contains(muscle)) {
                    muscleList.add(muscle);
                }
            }
            JSONArray secondary = exerciseArray.getJSONObject(i).getJSONArray("secondaryMuscles");
            for (int j = 0; j < secondary.length(); j++) {
                String muscle = secondary.getString(j);
                if (!muscleList.contains(muscle)) {
                    muscleList.add(muscle);
                }
            }
        }

        System.out.println(muscleList);

        WorkoutApp workoutApp = new WorkoutApp();
        workoutApp.open();

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
