package org.example;

public class Main {

    public static void main(String[] args) {
        String inputFileName = "input.txt";
        Solver solver = new Solver();
        long numberOfTokens = solver.computeNumberOfTokens(inputFileName);
        System.out.println("The numberOfTokens is " + numberOfTokens);
    }
}