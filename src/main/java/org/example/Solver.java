package org.example;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solver {

    public int computeDistinctPositionsCounter(String inputFileName) {
        List<String> lines = FileReaderUtil.readLinesFromFile(inputFileName);

        Position positionOfAgent = getPositionOfAgent(lines);
        if (positionOfAgent == null) {
            return 0;
        }

        Set<Position> positionVisitedByAgent = getPositionsVisitedByAgent(lines, positionOfAgent);

        return positionVisitedByAgent.size();
    }

    private Set<Position> getPositionsVisitedByAgent(List<String> lines, Position positionOfAgent) {
        Set<Position> positionsVisited = new HashSet<>();
        Direction direction = Direction.UP;

        while (isPositionInsideTheMap(lines, positionOfAgent)) {
            positionsVisited.add(positionOfAgent);

            Position nextPosition = getNextPosition(positionOfAgent, direction);
            while (isPositionBlocked(lines, nextPosition)) {
                direction = direction.getNextDirection();
                nextPosition = getNextPosition(positionOfAgent, direction);
            }
            positionOfAgent = nextPosition;
        }
        return positionsVisited;
    }

    private Position getNextPosition(Position positionOfAgent, Direction direction) {
        return new Position(positionOfAgent.row + direction.rowAdder, positionOfAgent.column + direction.columnAdder);
    }

    private boolean isPositionBlocked(List<String> lines, Position position) {
        if (!isPositionInsideTheMap(lines, position)) {
            return false;
        }
        return lines.get(position.row).charAt(position.column) == '#';
    }

    private boolean isPositionInsideTheMap(List<String> lines, Position position) {
        return position.row >= 0 && position.row < lines.size() &&
                position.column >= 0 && position.column < lines.get(0).length();
    }

    private Position getPositionOfAgent(List<String> lines) {
        for (int rowIndex = 0; rowIndex < lines.size(); rowIndex++) {
            if (lines.get(rowIndex).contains("^")) {
                int columnIndex = lines.get(rowIndex).indexOf("^");
                return new Position(rowIndex, columnIndex);
            }
        }
        return null;
    }


}
