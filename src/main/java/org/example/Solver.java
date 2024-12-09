package org.example;

import java.util.List;

public class Solver {

    private static final String MUL_PREFIX = "mul(";
    private static final String DO_PATTERN = "do()";
    private static final String DO_NOT_PATTERN = "don't()";
    private boolean doActivated = true;

    public int computeMultiplicationSum(String inputFileName) {
        List<String> lines = FileReaderUtil.readLinesFromFile(inputFileName);

        int multiplicationSum = 0;
        for (String line : lines) {
            int sum = parseLine(line);
            multiplicationSum += sum;
        }

        return multiplicationSum;
    }

    private int parseLine(String line) {
        int sum = 0;

        StringBuilder wordFormed = new StringBuilder();
        for (int index = 0; index < line.length(); index++) {
            wordFormed.append(line.charAt(index));

            if (DO_PATTERN.contentEquals(wordFormed)) { // if the word formed until now is do() then we can add to sum mul operations
                doActivated = true;
                wordFormed = new StringBuilder();
            }
            // if the word formed until now is a subset of do() then we can continue with the next character
            if (DO_PATTERN.startsWith(wordFormed.toString())) continue;


            if (DO_NOT_PATTERN.contentEquals(wordFormed)) { // if the word formed until now is don't() then we can skip mul operations
                doActivated = false;
                wordFormed = new StringBuilder();
            }
            // if the word formed until now is a subset of don't() then we can continue with the next character
            if (DO_NOT_PATTERN.startsWith(wordFormed.toString())) continue;

            if (!doActivated) { // if do or dont pattern does not match and do is not activated we are not interested in the sequence
                wordFormed = new StringBuilder();
                continue;
            }

            // if do is activated but the word formed by now has the prefix of multiplication, continue with the parsing
            if (MUL_PREFIX.startsWith(wordFormed.toString())) continue;

            // if do is activated and the formed word contains mul prefix and has a new character, we check if this char
            // is ) and then we surely have a pattern mul(*) and in getSumFromString we try to check if * is number,number
            if (wordFormed.toString().contains(MUL_PREFIX)) {
                if (line.charAt(index) == ')') {
                    sum += getSumFromString(wordFormed.toString());
                } else { // if the char is not ) then we check if it is an accepted character for the parameters of mul function
                    int finalIndex = index;
                    if ("1234567890,".chars().anyMatch(character -> character == line.charAt(finalIndex))) {
                        continue; // if it is an accepted char we continue with the next char parsing.
                    }
                }
            }
            wordFormed = new StringBuilder();
        }
        return sum;
    }

    private int getSumFromString(String wordFormed) {
        // we remove the mul( and ) from the wordFormed.
        wordFormed = wordFormed.replace("mul(", "").replace(")", "");
        // after this we split by ',' and check if there are 2 arguments, if yes, it means we have 2 args, most likely numbers
        // since we made sure above that the char accepted are digits and ',' only.
        String[] numbers = wordFormed.split(",");
        if (numbers.length != 2) return 0;
        int firstNumber = Integer.parseInt(numbers[0]);
        int secondNumber = Integer.parseInt(numbers[1]);
        return firstNumber * secondNumber;
    }
}
