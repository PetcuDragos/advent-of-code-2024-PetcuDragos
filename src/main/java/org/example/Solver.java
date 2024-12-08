package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solver {

    public int computeDistance(String inputFileName) {
        List<String> lines = FileReaderUtil.readLinesFromFile(inputFileName);

        Pair<List<Integer>, List<Integer>> pairOfMeasurements = getPairOfMeasurements(lines);
        List<Integer> firstMeasurements = pairOfMeasurements.first;
        List<Integer> secondMeasurements = pairOfMeasurements.second;
        firstMeasurements.sort(Comparator.naturalOrder());
        secondMeasurements.sort(Comparator.naturalOrder());

        int distance = 0;
        for (int index = 0; index < firstMeasurements.size(); index++) {
            distance += Math.abs((firstMeasurements.get(index) - secondMeasurements.get(index)));
        }

        return distance;
    }

    private Pair<List<Integer>, List<Integer>> getPairOfMeasurements(List<String> lines) {
        List<Integer> firstMeasurementList = new ArrayList<>();
        List<Integer> secondMeasurementList = new ArrayList<>();
        lines.forEach(line -> {
            String[] strings = line.split(" ", 2);
            int firstNumber = Integer.parseInt(strings[0].strip());
            int secondNumber = Integer.parseInt(strings[1].strip());
            firstMeasurementList.add(firstNumber);
            secondMeasurementList.add(secondNumber);
        });
        return new Pair<>(firstMeasurementList, secondMeasurementList);
    }
}
