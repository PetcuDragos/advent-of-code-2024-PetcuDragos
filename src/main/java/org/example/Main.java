package org.example;

public class Main {

    public static void main(String[] args) {
        String inputFileName = "input.txt";
        Solver solver = new Solver();
        int middlePageSumForCorrectUpdates = solver.computeMiddlePageSumForCorrectUpdates(inputFileName);
        System.out.println("MiddlePageSumForCorrectUpdates is " + middlePageSumForCorrectUpdates);
    }
}