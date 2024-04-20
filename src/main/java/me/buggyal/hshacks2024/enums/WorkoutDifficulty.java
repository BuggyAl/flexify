package me.buggyal.hshacks2024.enums;

public enum WorkoutDifficulty {

    BEGINNER("Beginner", "beginner"),
    INTERMEDIATE("Intermediate", "intermediate"),
    EXPERT("Expert", "expert"),

    ;

    final String name;
    final String dataKey;

    WorkoutDifficulty(String name, String dataKey) {
        this.name = name;
        this.dataKey = dataKey;
    }

    public String getDataKey() {
        return dataKey;
    }

    public static WorkoutDifficulty fromString(String difficulty) {
        for (WorkoutDifficulty workoutDifficulty : WorkoutDifficulty.values()) {
            if (workoutDifficulty.name.equalsIgnoreCase(difficulty)) {
                return workoutDifficulty;
            }
        }
        return null;
    }

}
