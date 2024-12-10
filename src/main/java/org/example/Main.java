package org.example;

public class Main {

    public static void main(String[] args) {
        String inputFileName = "input.txt";
        Solver solver = new Solver();
        int distinctPositionsCounter = solver.computeDistinctPositionsCounter(inputFileName);
        System.out.println("Distinct position counter is " + distinctPositionsCounter);
    }
}