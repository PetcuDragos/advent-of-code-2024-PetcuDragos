package org.example;

import java.util.ArrayList;
import java.util.List;

public class Region {

    private final List<Position> positions;

    public Region() {
        this.positions = new ArrayList<>();
    }

    public void addPosition(Position position) {
        positions.add(position);
    }

    public List<Position> getPositions() {
        return positions;
    }
}
