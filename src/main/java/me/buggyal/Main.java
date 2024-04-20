package me.buggyal;

import org.json.JSONArray;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        try {
            parseExercises();
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void parseExercises() throws URISyntaxException, IOException {
        Path path = Paths.get(Main.class.getResource("/exercises.json").toURI());
        String jsonContent = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        JSONArray exerciseArray = new JSONArray(jsonContent);
        System.out.println(exerciseArray.toString(2));
    }

}
