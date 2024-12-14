package org.example;

public enum Direction {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1);

    private final int rowModifier;
    private final int columnModifier;
    Direction(int rowModifier, int columnModifier) {
        this.rowModifier = rowModifier;
        this.columnModifier = columnModifier;
    }

    public int getRowModifier() {
        return rowModifier;
    }

    public int getColumnModifier() {
        return columnModifier;
    }
}
