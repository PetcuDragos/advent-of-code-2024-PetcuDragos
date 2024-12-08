package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solver {

    public int computeSimilarityScore(String inputFileName) {
        List<String> lines = FileReaderUtil.readLinesFromFile(inputFileName);

        Pair<List<Integer>, List<Integer>> pairOfMeasurements = getPairOfMeasurements(lines);
        List<Integer> firstMeasurements = pairOfMeasurements.first;
        List<Integer> secondMeasurements = pairOfMeasurements.second;
        firstMeasurements.sort(Comparator.naturalOrder());
        secondMeasurements.sort(Comparator.naturalOrder());

        int similarityScore = 0;
        for (int firstMeasurementIndex = 0; firstMeasurementIndex < firstMeasurements.size(); firstMeasurementIndex++) {

            int value = firstMeasurements.get(firstMeasurementIndex);
            int valueOccurrencesInFirstMeasurement = getValueOccurrencesInSortedList(value, firstMeasurements);
            int valueOccurrencesInSecondMeasurement = getValueOccurrencesInSortedList(value, secondMeasurements);

            similarityScore += valueOccurrencesInFirstMeasurement * (value * valueOccurrencesInSecondMeasurement);
            // skip values from first measurement equal to the current one
            firstMeasurementIndex += valueOccurrencesInFirstMeasurement - 1;
        }

        return similarityScore;
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

    private int getValueOccurrencesInSortedList(int value, List<Integer> list) {

        int indexWhereValueWasFound = getAppearanceIndexOfValueInSortedList(value, list);
        if (indexWhereValueWasFound == -1) {
            return 0;
        }
        int firstIndex = computeFirstIndexWhereValueIsFound(list, indexWhereValueWasFound);
        int lastIndex = computeLastIndexWhereValueIsFound(list, indexWhereValueWasFound);

        return lastIndex - firstIndex + 1;
    }

    private int computeFirstIndexWhereValueIsFound(List<Integer> list, int indexWhereValueWasFound) {
        int index = indexWhereValueWasFound;
        while (index > 0) {
            int currentValue = list.get(index);
            int previousValue = list.get(index - 1);
            if (currentValue != previousValue) {
                break;
            }
            index--;
        }
        return index;
    }

    private int computeLastIndexWhereValueIsFound(List<Integer> list, int indexWhereValueWasFound) {
        int index = indexWhereValueWasFound;
        while (index < list.size() - 1) {
            int currentValue = list.get(index);
            int nextValue = list.get(index + 1);
            if (currentValue != nextValue) {
                break;
            }
            index++;
        }
        return index;
    }

    /**
     * Returns the index of an element from the list that has the value equal to the given value
     * It is not necessarily the first index or the last index, it is random so further computation must be done to
     * compute the first/last appearance.
     */
    private int getAppearanceIndexOfValueInSortedList(int givenValue, List<Integer> list) {
        int leftIndex = 0;
        int rightIndex = list.size();

        while (leftIndex <= rightIndex) {
            int middleIndex = (leftIndex + rightIndex) / 2;

            int value = list.get(middleIndex);
            if (value < givenValue) {
                leftIndex = middleIndex + 1;
            } else if (value > givenValue) {
                rightIndex = middleIndex - 1;
            } else {
                return middleIndex;
            }
        }
        return -1;
    }
}
