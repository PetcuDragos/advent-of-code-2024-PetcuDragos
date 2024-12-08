package org.example;

public class Main {

    public static void main(String[] args) {
        String inputFileName = "input.txt";
        Solver solver = new Solver();
        int numberOfSafeReports = solver.computeNumberOfSafeReports(inputFileName);
        System.out.println("The number of safe reports is " + numberOfSafeReports);
    }
}