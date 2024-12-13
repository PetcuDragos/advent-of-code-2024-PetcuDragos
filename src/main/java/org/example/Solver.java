package org.example;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Solver {

    public long computeTrailheadScores(String inputFileName) {
        List<String> lines = FileReaderUtil.readLinesFromFile(inputFileName);

        List<List<Peak>> map = getTopographicMap(lines);
        List<Position> trailheadPositions = getTrailheadPositions(lines);
        simulateHikingForTrailheadPositions(map, trailheadPositions);

        return getTrailheadScores(map);
    }

    private int getTrailheadScores(List<List<Peak>> map) {
        int score = 0;
        for (List<Peak> mapRow : map) {
            for (Peak peak : mapRow) {
                if (peak.getHeight() == 9) score += peak.getVisitors().size();
            }
        }
        return score;
    }

    private void simulateHikingForTrailheadPositions(List<List<Peak>> map, List<Position> trailheadPositions) {
        for (int trailheadIndex = 0; trailheadIndex < trailheadPositions.size(); trailheadIndex++) {
            simulateHikingForTrailheadPosition(map, trailheadPositions.get(trailheadIndex), trailheadIndex);
        }
    }

    private void simulateHikingForTrailheadPosition(List<List<Peak>> map, Position trailheadPosition, int visitorIndex) {
        Queue<Position> trailheadNextPositions = new ArrayDeque<>();
        trailheadNextPositions.add(trailheadPosition);

        while (!trailheadNextPositions.isEmpty()) {
            Position currentPosition = trailheadNextPositions.remove();

            for (Direction direction : Direction.values()) {
                Position nextPosition = getNextPosition(currentPosition, direction);

                if (!isMovePossible(currentPosition, nextPosition, map) ||
                        positionAlreadyVisited(nextPosition, map, visitorIndex)) {
                    continue;
                }

                markPositionAsVisited(nextPosition, map, visitorIndex);
                trailheadNextPositions.add(nextPosition);
            }
        }
    }

    private void markPositionAsVisited(Position position, List<List<Peak>> map, int trailColor) {
        map.get(position.getRow()).get(position.getColumn()).addVisitor(trailColor);
    }

    private boolean positionAlreadyVisited(Position position, List<List<Peak>> map, int visitorIndex) {
        return map.get(position.getRow()).get(position.getColumn()).getVisitors().contains(visitorIndex);
    }

    private boolean isMovePossible(Position currentPosition, Position nextPosition, List<List<Peak>> map) {
        return nextPosition.getRow() >= 0 && nextPosition.getRow() < map.size() &&
                nextPosition.getColumn() >= 0 && nextPosition.getColumn() < map.get(0).size() &&
                map.get(currentPosition.getRow()).get(currentPosition.getColumn()).getHeight() + 1 ==
                        map.get(nextPosition.getRow()).get(nextPosition.getColumn()).getHeight();
    }

    public Position getNextPosition(Position position, Direction direction) {
        return switch (direction) {
            case UP -> new Position(position.getRow() - 1, position.getColumn());
            case DOWN -> new Position(position.getRow() + 1, position.getColumn());
            case LEFT -> new Position(position.getRow(), position.getColumn() - 1);
            case RIGHT -> new Position(position.getRow(), position.getColumn() + 1);
        };
    }

    private List<List<Peak>> getTopographicMap(List<String> rows) {
        List<List<Peak>> map = new ArrayList<>();

        for (String row : rows) {
            List<Peak> mapRow = new ArrayList<>();
            for (int columnIndex = 0; columnIndex < row.length(); columnIndex++) {
                int height = Integer.parseInt(String.valueOf(row.charAt(columnIndex)));
                mapRow.add(new Peak(height));
            }
            map.add(mapRow);
        }
        return map;
    }

    private List<Position> getTrailheadPositions(List<String> rows) {
        List<Position> trailheadPositions = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < rows.size(); rowIndex++) {
            String row = rows.get(rowIndex);
            for (int columnIndex = 0; columnIndex < row.length(); columnIndex++) {
                if (row.charAt(columnIndex) == '0') {
                    trailheadPositions.add(new Position(rowIndex, columnIndex));
                }
            }
        }
        return trailheadPositions;
    }
}
