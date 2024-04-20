package me.buggyal;

public enum WorkoutType {

    // [abdominals, hamstrings, calves, shoulders, adductors, glutes, quadriceps, biceps, forearms, abductors, triceps, chest, lower back, traps, middle back, lats, neck]

    ARMS("shoulders", "biceps", "forearms", "triceps", "triceps"),
    UPPER_TORSO("chest", "lower back", "traps", "middle back", "lats"),
    LEGS("hamstrings", "calves", "addctors", "glutes", "quadriceps", "abductors"),
    CORE("abdominals", "neck")
    ;

    final String[] applicableMuscles;

    WorkoutType(String... applicableMuscles) {
        this.applicableMuscles = applicableMuscles;
    }

}
