package org.example;

public class Main {

    public static void main(String[] args) {
        String inputFileName = "input.txt";
        Solver solver = new Solver();
        long computeChecksum = solver.computeChecksum(inputFileName);
        System.out.println("Checksum is " + computeChecksum);
    }
}