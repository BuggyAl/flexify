package me.buggyal.hshacks2024.enums;

import java.util.List;

public enum WorkoutTarget {

    // [abdominals, hamstrings, calves, shoulders, adductors, glutes, quadriceps, biceps, forearms, abductors, triceps, chest, lower back, traps, middle back, lats, neck]

    ARMS("Arms", "shoulders", "biceps", "forearms", "triceps"),
    UPPER_TORSO("Upper Torso", "chest", "lower back", "traps", "middle back", "lats"),
    LEGS("Legs", "hamstrings", "calves", "addctors", "glutes", "quadriceps", "abductors"),
    CORE("Core", "abdominals", "neck"),
    FULL_BODY("Full Body", "biceps", "triceps", "chest", "lats", "hamstrings", "quadriceps", "abdominals")

    ;

    final String name;
    final String[] applicableMuscles;

    WorkoutTarget(String name, String... applicableMuscles) {
        this.name = name;
        this.applicableMuscles = applicableMuscles;
    }

    public List<String> getApplicableMuscles() {
        return List.of(applicableMuscles);
    }

    public static WorkoutTarget fromString(String target) {
        for (WorkoutTarget workoutTarget : WorkoutTarget.values()) {
            if (workoutTarget.name.equalsIgnoreCase(target)) {
                return workoutTarget;
            }
        }
        return null;
    }

}
