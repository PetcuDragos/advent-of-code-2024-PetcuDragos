package org.example;

public class Position {
    private final int row;
    private final int column;
    private final char plantType;
    private boolean processed;
    private int fences;

    public Position(int row, int column, char plantType) {
        this.row = row;
        this.column = column;
        this.plantType = plantType;
        processed = false;
        fences = 0;
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

    public void increaseNumberOfFences() {
        fences++;
    }

    public int getFences() {
        return fences;
    }
}
