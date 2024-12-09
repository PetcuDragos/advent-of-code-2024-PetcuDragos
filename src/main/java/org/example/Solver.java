package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Solver {

    private final static String XMAS = "XMAS";
    private final static String SAMX = "SAMX";

    public int computeXmasCounter(String inputFileName) {
        List<String> rows = FileReaderUtil.readLinesFromFile(inputFileName);
        List<String> columns = getColumns(rows);
        List<String> mainDiagonals = getMainDiagonals(rows);
        List<String> secondaryDiagonals = getSecondaryDiagonals(rows);

        long occurrencesOnRows = rows.stream()
                .mapToLong(row -> Pattern.compile(XMAS).matcher(row).results().count() +
                        Pattern.compile(SAMX).matcher(row).results().count()).sum();
        long occurrencesOnColumns = columns.stream()
                .mapToLong(column -> Pattern.compile(XMAS).matcher(column).results().count() +
                        Pattern.compile(SAMX).matcher(column).results().count()).sum();
        long occurrencesOnMainDiagonals = mainDiagonals.stream()
                .mapToLong(diagonal -> Pattern.compile(XMAS).matcher(diagonal).results().count() +
                        Pattern.compile(SAMX).matcher(diagonal).results().count()).sum();
        long occurrencesOnSecondaryDiagonals = secondaryDiagonals.stream()
                .mapToLong(diagonal -> Pattern.compile(XMAS).matcher(diagonal).results().count() +
                        Pattern.compile(SAMX).matcher(diagonal).results().count()).sum();

        return (int) (occurrencesOnRows + occurrencesOnColumns + occurrencesOnMainDiagonals + occurrencesOnSecondaryDiagonals);
    }

    private List<String> getColumns(List<String> rows) {
        int rowNumber = rows.size();
        int columnNumber = rows.get(0).length();
        List<String> columns = new ArrayList<>();

        for (int columnIndex = 0; columnIndex < columnNumber; columnIndex++) {
            StringBuilder column = new StringBuilder();
            for (int rowIndex = 0; rowIndex < rowNumber; rowIndex++) {
                column.append(rows.get(rowIndex).charAt(columnIndex));
            }
            columns.add(column.toString());
        }
        return columns;
    }

    private List<String> getMainDiagonals(List<String> rows) {
        int rowNumber = rows.size();
        int columnNumber = rows.get(0).length();
        List<String> mainDiagonalWords = new ArrayList<>();

        int rowIndex = 0;
        int columnIndex = 0;
        while (columnIndex < columnNumber) {
            String wordFromMainDiagonal = getMainDiagonalStartingFrom(rowIndex, columnIndex, rows);
            mainDiagonalWords.add(wordFromMainDiagonal);
            columnIndex++;
        }

        rowIndex = 1;
        columnIndex = 0;
        while (rowIndex < rowNumber) {
            String wordFromMainDiagonal = getMainDiagonalStartingFrom(rowIndex, columnIndex, rows);
            mainDiagonalWords.add(wordFromMainDiagonal);
            rowIndex++;
        }

        return mainDiagonalWords;
    }

    private String getMainDiagonalStartingFrom(int rowIndex, int columnIndex, List<String> rows) {
        int rowNumber = rows.size();
        int columnNumber = rows.get(0).length();
        StringBuilder word = new StringBuilder();

        while (rowIndex < rowNumber && columnIndex < columnNumber) {
            word.append(rows.get(rowIndex).charAt(columnIndex));
            rowIndex++;
            columnIndex++;
        }
        return word.toString();
    }

    private List<String> getSecondaryDiagonals(List<String> rows) {
        int rowNumber = rows.size();
        int columnNumber = rows.get(0).length();
        List<String> secondaryDiagonalWords = new ArrayList<>();

        int rowIndex = 0;
        int columnIndex = 0;
        while (rowIndex < rowNumber) {
            String wordFromSecondaryDiagonal = getSecondaryDiagonalStartingFrom(rowIndex, columnIndex, rows);
            secondaryDiagonalWords.add(wordFromSecondaryDiagonal);
            rowIndex++;
        }

        rowIndex = rows.size() - 1;
        columnIndex = 1;
        while (columnIndex < columnNumber) {
            String wordFromSecondaryDiagonal = getSecondaryDiagonalStartingFrom(rowIndex, columnIndex, rows);
            secondaryDiagonalWords.add(wordFromSecondaryDiagonal);
            columnIndex++;
        }

        return secondaryDiagonalWords;
    }

    private String getSecondaryDiagonalStartingFrom(int rowIndex, int columnIndex, List<String> rows) {
        int columnNumber = rows.get(0).length();
        StringBuilder word = new StringBuilder();

        while (rowIndex >= 0 && columnIndex < columnNumber) {
            word.append(rows.get(rowIndex).charAt(columnIndex));
            rowIndex--;
            columnIndex++;
        }
        return word.toString();
    }
}
