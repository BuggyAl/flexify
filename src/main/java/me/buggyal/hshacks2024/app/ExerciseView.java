package me.buggyal.hshacks2024.app;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import me.buggyal.hshacks2024.Main;
import me.buggyal.hshacks2024.Workout;

import java.util.List;

public class ExerciseView extends VBox {

    public ExerciseView() {

        List<Workout.WorkoutExercise> exercises = Main.workout.generateWorkout();

        ListView<Workout.WorkoutExercise> listView = new ListView<>();
        listView.getItems().addAll(exercises);
        listView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Workout.WorkoutExercise item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    StringBuilder text = new StringBuilder(item.name() + "\n");
                    for (String instruction : item.instructions()) text.append(instruction).append("\n");
                    text.append(item.sets()).append(" x ").append(item.reps());
                    setText(text.toString());
                }
            }
        });

        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // make the width span the entire window but add margins on the sides
        VBox.setVgrow(listView, Priority.ALWAYS);
        setSpacing(10);

        getChildren().add(listView);
    }
}