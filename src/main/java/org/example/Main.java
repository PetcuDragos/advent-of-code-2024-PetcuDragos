package org.example;

public class Main {

    public static void main(String[] args) {
        String inputFileName = "input.txt";
        Solver solver = new Solver();
        int multiplicationSum = solver.computeMultiplicationSum(inputFileName);
        System.out.println("Multiplication sum is " + multiplicationSum);
    }
}