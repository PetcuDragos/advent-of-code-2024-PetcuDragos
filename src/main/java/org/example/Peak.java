package org.example;

import java.util.ArrayList;
import java.util.List;

public class Peak {
    private final int height;
    private final List<Integer> visitors;

    public Peak(int height) {
        this.height = height;
        visitors = new ArrayList<>();
    }

    public int getHeight() {
        return height;
    }

    public List<Integer> getVisitors() {
        return visitors;
    }

    public void addVisitor(int visitorIndex) {
        visitors.add(visitorIndex);
    }
}
