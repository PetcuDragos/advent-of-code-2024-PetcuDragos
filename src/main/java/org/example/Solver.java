package org.example;

import java.util.*;

public class Solver {

    public long computePrice(String inputFileName) {
        List<String> lines = FileReaderUtil.readLinesFromFile(inputFileName);
        List<List<Position>> map = createMap(lines);
        List<Region> regions = getRegions(map);

        return calculatePrice(regions, map);
    }

    private long calculatePrice(List<Region> regions, List<List<Position>> map) {
        int price = 0;
        for (Region region : regions) {
            price += calculatePerimeter(region, map) * calculateArea(region);
        }
        return price;
    }

    private int calculateArea(Region region) {
        return region.getPositions().size();
    }

    private int calculatePerimeter(Region region, List<List<Position>> map) {
        int perimeter = 0;
        for (Position position : region.getPositions()) {
            int numberOfFencesNeeded = position.getNumberOfFences();

            Position upPosition = getNextPosition(position, Direction.UP, map);
            Position leftPosition = getNextPosition(position, Direction.LEFT, map);

            if (arePlantTypesTheSame(position, leftPosition)) {
                if (leftPosition.isThereFenceInDirection(Direction.UP) && position.isThereFenceInDirection(Direction.UP)) {
                    numberOfFencesNeeded --;
                }
                if (leftPosition.isThereFenceInDirection(Direction.DOWN) && position.isThereFenceInDirection(Direction.DOWN)) {
                    numberOfFencesNeeded --;
                }
            }
            if (arePlantTypesTheSame(position, upPosition)) {
                if (upPosition.isThereFenceInDirection(Direction.LEFT) && position.isThereFenceInDirection(Direction.LEFT)) {
                    numberOfFencesNeeded --;
                }
                if (upPosition.isThereFenceInDirection(Direction.RIGHT) && position.isThereFenceInDirection(Direction.RIGHT)) {
                    numberOfFencesNeeded --;
                }
            }
            perimeter += numberOfFencesNeeded;
        }
        return perimeter;
    }

    private boolean arePlantTypesTheSame(Position firstPosition, Position secondPosition) {
        if (firstPosition == null || secondPosition == null) {
            return false;
        }
        return firstPosition.getPlantType() == secondPosition.getPlantType();
    }

    private List<List<Position>> createMap(List<String> rows) {
        List<List<Position>> map = new ArrayList<>();

        for (int rowIndex = 0; rowIndex < rows.size(); rowIndex++) {
            String row = rows.get(rowIndex);
            List<Position> mapRow = new ArrayList<>();
            for (int columnIndex = 0; columnIndex < row.length(); columnIndex++) {
                char plantType = row.charAt(columnIndex);
                mapRow.add(new Position(rowIndex, columnIndex, plantType));
            }
            map.add(mapRow);
        }
        return map;
    }

    private List<Region> getRegions(List<List<Position>> map) {
        List<Region> regions = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < map.size(); rowIndex++) {
            for (int columnIndex = 0; columnIndex < map.get(0).size(); columnIndex++) {
                Position position = map.get(rowIndex).get(columnIndex);
                if (!position.isProcessed()) {
                    regions.add(createRegion(map, position));
                }
            }
        }
        return regions;
    }

    private Region createRegion(List<List<Position>> map, Position startingPosition) {

        Queue<Position> unprocessedPositions = new ArrayDeque<>();
        unprocessedPositions.add(startingPosition);
        Region region = new Region();

        while (!unprocessedPositions.isEmpty()) {
            Position currentPosition = unprocessedPositions.remove();

            if (currentPosition.isProcessed()) continue;
            region.addPosition(currentPosition);
            currentPosition.setProcessed(true);

            for (Direction direction : Direction.values()) {
                Position nextPosition = getNextPosition(currentPosition, direction, map);

                if (nextPosition == null || !arePositionsFromSameRegion(currentPosition, nextPosition)) {
                    currentPosition.addNewFenceInDirection(direction);
                    continue;
                }
                if (nextPosition.isProcessed()) {
                    continue;
                }

                unprocessedPositions.add(nextPosition);
            }
        }
        return region;
    }

    private boolean arePositionsFromSameRegion(Position firstPosition, Position secondPosition) {
        return firstPosition.getPlantType() == secondPosition.getPlantType();
    }

    public Position getNextPosition(Position position, Direction direction, List<List<Position>> map) {
        int newRowIndex = position.getRow() + direction.getRowModifier();
        int newColumnIndex = position.getColumn() + direction.getColumnModifier();
        if (!isPositionInsideTheMap(map, newRowIndex, newColumnIndex)) {
            return null;
        }
        return map.get(newRowIndex).get(newColumnIndex);
    }

    private boolean isPositionInsideTheMap(List<List<Position>> map, int rowIndex, int columnIndex) {
        return rowIndex >= 0 && rowIndex < map.size() && columnIndex >= 0 && columnIndex < map.get(0).size();
    }
}
