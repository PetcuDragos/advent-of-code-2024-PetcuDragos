package org.example;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class Solver {

    public int computeMultiplicationSum(String inputFileName) {
        List<String> lines = FileReaderUtil.readLinesFromFile(inputFileName);

        int multiplicationSum = 0;
        for (String line : lines) {
            Pattern pattern = Pattern.compile("mul\\([0-9]{1,3},[0-9]{1,3}\\)");
            List<String> correctMultiplicationStrings =
                    pattern.matcher(line).results().map(MatchResult::group).toList();

            for (String correctMultiplicationString : correctMultiplicationStrings) {
                String[] numbers = correctMultiplicationString
                        .replace("mul(", "")
                        .replace(")", "")
                        .split(",");
                multiplicationSum += Integer.parseInt(numbers[0]) * Integer.parseInt(numbers[1]);
            }
        }

        return multiplicationSum;
    }
}
