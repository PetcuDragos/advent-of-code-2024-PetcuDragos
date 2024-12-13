package org.example;

public class Peak {
    private final int height;
    private int visits;

    public Peak(int height) {
        this.height = height;
        visits = 0;
    }

    public int getHeight() {
        return height;
    }

    public int getVisits() {
        return visits;
    }

    public void increaseNumberOfVisits() {
        visits++;
    }
}
