package org.example;

public class Main {

    public static void main(String[] args) {
        String inputFileName = "input.txt";
        Solver solver = new Solver();
        int xmasCounter = solver.computeXmasCounter(inputFileName);
        System.out.println("XmasCounter is " + xmasCounter);
    }
}