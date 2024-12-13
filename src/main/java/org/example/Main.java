package org.example;

public class Main {

    public static void main(String[] args) {
        String inputFileName = "input.txt";
        Solver solver = new Solver();
        long trailheadScores = solver.computeTrailheadRatings(inputFileName);
        System.out.println("The trailhead sum of ratings is " + trailheadScores);
    }
}