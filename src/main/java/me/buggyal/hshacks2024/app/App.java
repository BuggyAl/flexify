package me.buggyal.hshacks2024.app;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import me.buggyal.hshacks2024.Main;
import me.buggyal.hshacks2024.enums.WorkoutDifficulty;
import me.buggyal.hshacks2024.enums.WorkoutTarget;
import me.buggyal.hshacks2024.enums.WorkoutType;

public class App extends Application {

    public ComboBox<String> difficultyComboBox;
    public ComboBox<String> typeComboBox;
    public ComboBox<String> targetComboBox;

    public String difficulty = "Beginner";
    public String type = "Strength";
    public String target = "Arms";

    int width = 800;
    int height = 600;

    Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Workout App");
        primaryStage.setWidth(width);
        primaryStage.setHeight(height);
        primaryStage.getIcons().add(new Image("file:icon.png"));
        primaryStage.setScene(updateWindow());
        primaryStage.show();
    }

    public Scene updateWindow() {
        // Create ImageView for the logo
        ImageView logoImageView = new ImageView(new Image("file:src/main/resources/logo.png"));
        logoImageView.setFitWidth(200);
        logoImageView.setFitHeight(200);

        // Create Dropdowns
        HBox dropdowns = new HBox(3);
        dropdowns.setAlignment(Pos.CENTER);

        ComboBox<String> difficultyComboBoxInternal = new ComboBox<>();
        this.difficultyComboBox = difficultyComboBoxInternal;
        difficultyComboBoxInternal.getItems().addAll("Beginner", "Intermediate", "Expert");
        difficultyComboBoxInternal.setValue(difficulty);
        Main.workout.workoutDifficulty = WorkoutDifficulty.fromString(difficulty);
        difficultyComboBoxInternal.addEventHandler(ActionEvent.ACTION, e -> difficulty = difficultyComboBoxInternal.getValue());

        ComboBox<String> typeComboBoxInternal = new ComboBox<>();
        this.typeComboBox = typeComboBoxInternal;
        typeComboBoxInternal.getItems().addAll("Strength", "Plyometrics", "Strongman", "Powerlifting", "Olympic Weightlifting");
        typeComboBoxInternal.setValue(type);
        Main.workout.workoutType = WorkoutType.fromString(type);
        typeComboBoxInternal.addEventHandler(ActionEvent.ACTION, e -> type = typeComboBoxInternal.getValue());

        ComboBox<String> targetComboBoxInternal = new ComboBox<>();
        targetComboBoxInternal.getItems().addAll("Arms", "Upper Torso", "Legs", "Core", "Full Body");
        this.targetComboBox = targetComboBoxInternal;
        Main.workout.workoutTarget = WorkoutTarget.fromString(target);
        targetComboBoxInternal.setValue(target);
        targetComboBoxInternal.addEventHandler(ActionEvent.ACTION, e -> target = targetComboBoxInternal.getValue());

        dropdowns.getChildren().addAll(difficultyComboBoxInternal, typeComboBoxInternal, targetComboBoxInternal);

        Button submitButton = new Button("Generate a Workout");
        submitButton.setOnAction(e -> {
            primaryStage.setScene(updateWindow());
            primaryStage.setX(primaryStage.getX());
            primaryStage.setY(primaryStage.getY());
            primaryStage.setWidth(primaryStage.getWidth());
            primaryStage.setHeight(primaryStage.getHeight());
            // set the dropdown values to the selected values
            difficulty = difficultyComboBoxInternal.getValue();
            type = typeComboBoxInternal.getValue();
            target = targetComboBoxInternal.getValue();
        });

        ExerciseView exerciseView = new ExerciseView();

        VBox vBox = new VBox(20);
        vBox.getChildren().addAll(logoImageView, dropdowns, submitButton, exerciseView);

        vBox.setStyle("-fx-background-color: black;");
        StackPane root = new StackPane();
        vBox.setAlignment(Pos.CENTER);

        vBox.heightProperty().addListener((obs, oldVal, newVal) -> exerciseView.setPrefHeight(newVal.doubleValue() - 200));

        StackPane.setAlignment(vBox, Pos.CENTER);
        root.getChildren().add(vBox);
        return new Scene(root);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void runApp() {
        launch();
    }

}
