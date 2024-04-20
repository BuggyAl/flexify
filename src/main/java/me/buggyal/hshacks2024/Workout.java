package me.buggyal.hshacks2024;

import javafx.scene.image.Image;
import me.buggyal.hshacks2024.enums.RepMode;
import me.buggyal.hshacks2024.enums.WorkoutDifficulty;
import me.buggyal.hshacks2024.enums.WorkoutTarget;
import me.buggyal.hshacks2024.enums.WorkoutType;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Workout {

    // Forces - Push, Pull, Static, null

    // Workout Difficulty
    // Workout Purpose
    // Target Area

    public WorkoutDifficulty workoutDifficulty;
    public WorkoutType workoutType;
    public WorkoutTarget workoutTarget;

    List<WorkoutExercise> exercises;

    public Workout(WorkoutDifficulty workoutDifficulty, WorkoutType workoutType, WorkoutTarget workoutTarget) {
        this.workoutDifficulty = workoutDifficulty;
        this.workoutType = workoutType;
        this.workoutTarget = workoutTarget;
        exercises = generateWorkout();
    }

    public List<WorkoutExercise> generateWorkout() {
        System.out.println("Generating workout for " + workoutDifficulty.name() + " " + workoutType.name() + " " + workoutTarget.name());
        // do the magic
        Random random = new Random();
        JSONArray exerciseArray = Main.exerciseArray;
        System.out.println(exerciseArray.length());
        List<WorkoutExercise> workoutExercises = new ArrayList<>();
        for (int i = 0; i < exerciseArray.length(); i++) {
            String exerciseDifficulty = exerciseArray.getJSONObject(i).getString("level");
            String type = exerciseArray.getJSONObject(i).getString("category");

            List<String> affectedMuscles = new ArrayList<>();
            JSONArray muscles = exerciseArray.getJSONObject(i).getJSONArray("primaryMuscles");
            for (int j = 0; j < muscles.length(); j++) {
                affectedMuscles.add(muscles.getString(j));
            }

            // if none of the affected muscles are in the target area, skip
            if (affectedMuscles.stream().noneMatch(muscle -> workoutTarget.getApplicableMuscles().contains(muscle)))
                continue;

            if (!exerciseDifficulty.equals(workoutDifficulty.getDataKey()) || !type.equals(workoutType.getDataKey())) {
                continue;
            }

            RepMode repMode = workoutType.getRepMode();
            // generate random image between minReps and maxReps of repMode
            int reps = random.nextInt(repMode.maxReps - repMode.minReps + 1) + repMode.minReps;
            // generate random image between minSets and maxSets of repMode
            int sets = random.nextInt(repMode.maxSets - repMode.minSets + 1) + repMode.minSets;

            List<String> instructions = new ArrayList<>();
            JSONArray instructionsArray = exerciseArray.getJSONObject(i).getJSONArray("instructions");
            for (int j = 0; j < instructionsArray.length(); j++) {
                instructions.add(instructionsArray.getString(j));
            }

            WorkoutExercise workoutExercise = new WorkoutExercise(
                    exerciseArray.getJSONObject(i).getString("id"),
                    exerciseArray.getJSONObject(i).getString("name"),
                    instructions,
                    sets,
                    reps
            );
            workoutExercises.add(workoutExercise);
        }

        List<WorkoutExercise> finalExercises = new ArrayList<>();
        // random number between 6 and 10
        int numExercises = random.nextInt(5) + 6;
        for (int i = 0; i < numExercises; i++) {
            // get a random exercise from workoutExercises
            if (workoutExercises.isEmpty()) {
                if (finalExercises.isEmpty()) {
                    WorkoutExercise noExerciseFound =
                            new WorkoutExercise(
                                    "no_exercise_found",
                                    "No Exercises Found!",
                                    List.of("Adjust the workout parameters and try again!"),
                                    0,
                                    0
                            );
                    finalExercises.add(noExerciseFound);
                    return finalExercises;
                }
                break;
            }
            WorkoutExercise exercise = workoutExercises.get(random.nextInt(workoutExercises.size()));
            finalExercises.add(exercise);
            workoutExercises.remove(exercise);
        }
        return finalExercises;
    }

    public List<WorkoutExercise> getWorkout() {
        return exercises;
    }

    public record WorkoutExercise(String exerciseID, String name, List<String> instructions, int sets, int reps) {
        public Image getImage() {
            return new Image("file:/main/resources/exercise_images/" + exerciseID + "0.jpeg");
        }
    }

}
