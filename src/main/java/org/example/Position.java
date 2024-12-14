package org.example;

import java.util.HashSet;
import java.util.Set;

public class Position {
    private final int row;
    private final int column;
    private final char plantType;
    private final Set<Direction> fences;
    private boolean processed;

    public Position(int row, int column, char plantType) {
        this.row = row;
        this.column = column;
        this.plantType = plantType;
        processed = false;
        fences = new HashSet<>();
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getPlantType() {
        return plantType;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public void addNewFenceInDirection(Direction fenceDirection) {
        fences.add(fenceDirection);
    }

    public int getNumberOfFences() {
        return fences.size();
    }

    public boolean isThereFenceInDirection(Direction direction) {
        return fences.contains(direction);
    }
}
