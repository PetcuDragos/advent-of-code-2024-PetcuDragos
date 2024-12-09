package org.example;

public class Main {

    public static void main(String[] args) {
        String inputFileName = "input.txt";
        Solver solver = new Solver();
        int numberOfSafeDampedReports = solver.computeNumberOfSafeDampedReports(inputFileName);
        System.out.println("The number of safe damped reports is " + numberOfSafeDampedReports);
    }
}