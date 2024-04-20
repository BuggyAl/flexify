package me.buggyal.hshacks2024.enums;

public enum RepMode {

    VERY_HIGH(15, 30, 2, 3),
    HIGH(8, 12, 2, 6),
    BALANCED(4, 8, 3, 5),
    LOW(2, 4, 3, 5),
    ;

    public final int minReps;
    public final int maxReps;
    public final int minSets;
    public final int maxSets;

      RepMode(int minReps, int maxReps, int minSets, int maxSets) {
        this.minReps = minReps;
        this.maxReps = maxReps;
        this.minSets = minSets;
        this.maxSets = maxSets;
    }

    public int getMinReps() {
        return minReps;
    }

    public int getMaxReps() {
        return maxReps;
    }

    public int getMinSets() {
        return minSets;
    }

    public int getMaxSets() {
        return maxSets;
    }

}
