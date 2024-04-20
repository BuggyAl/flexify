package me.buggyal.hshacks2024.enums;

public enum WorkoutType {

    // [strength, stretching, plyometrics, strongman, powerlifting, cardio, olympic weightlifting]

    STRENGTH("Strength", "strength", RepMode.BALANCED),
    PLYOMETRICS("Plyometrics", "plyometrics", RepMode.VERY_HIGH),
    STRONGMAN("Strongman", "strongman", RepMode.HIGH),
    POWERLIFTING("Powerlifting", "powerlifting", RepMode.HIGH),
    OLYMPIC_WEIGHTLIFTING("Olympic Weightlifting", "olympic weightlifting", RepMode.LOW);

    final String name;
    final String dataKey;
    final RepMode repMode;

    WorkoutType(String name, String dataKey, RepMode repMode) {
        this.name = name;
        this.dataKey = dataKey;
        this.repMode = repMode;
    }

    public String getDataKey() {
        return dataKey;
    }

    public RepMode getRepMode() {
        return repMode;
    }

    public static WorkoutType fromString(String type) {
        for (WorkoutType workoutType : WorkoutType.values()) {
            if (workoutType.name.equalsIgnoreCase(type)) {
                return workoutType;
            }
        }
        return null;
    }

}
