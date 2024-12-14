package org.example;

public class Main {

    public static void main(String[] args) {
        String inputFileName = "input.txt";
        Solver solver = new Solver();
        long price = solver.computePrice(inputFileName);
        System.out.println("The price is " + price);
    }
}