package org.example;

public class Main {

    public static void main(String[] args) {
        String inputFileName = "input.txt";
        Solver solver = new Solver();
        int distance = solver.computeSimilarityScore(inputFileName);
        System.out.println("Distance is " + distance);
    }
}