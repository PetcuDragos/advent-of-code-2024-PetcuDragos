package org.example;

public enum Direction {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1);

    public final int rowAdder;
    public final int columnAdder;

    Direction(int rowAdder, int columnAdder) {
        this.rowAdder = rowAdder;
        this.columnAdder = columnAdder;
    }

    public Direction getNextDirection() {
        return switch (this) {
            case UP -> RIGHT;
            case RIGHT -> DOWN;
            case DOWN -> LEFT;
            case LEFT -> UP;
        };
    }
}
