package org.example;

public class Main {

    public static void main(String[] args) {
        String inputFileName = "input.txt";
        Solver solver = new Solver();
        int masCounter = solver.computeMasCounter(inputFileName);
        System.out.println("MasCounter is " + masCounter);
    }
}