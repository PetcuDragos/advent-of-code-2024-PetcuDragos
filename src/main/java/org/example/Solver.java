package org.example;

import java.util.List;

public class Solver {

    private final static String MAS = "MAS";
    private final static String SAM = "SAM";

    public int computeMasCounter(String inputFileName) {
        List<String> rows = FileReaderUtil.readLinesFromFile(inputFileName);
        int sum = 0;

        int rowNumber = rows.size();
        int columnNumber = rows.get(0).length();

        for (int rowIndex = 1; rowIndex < rowNumber - 1; rowIndex++) {
            for (int columnIndex = 1; columnIndex < columnNumber - 1; columnIndex++) {
                String mainDiagonalWord = getMainDiagonalWord(rowIndex, columnIndex, rows);
                String secondaryDiagonalWord = getSecondaryDiagonalWord(rowIndex, columnIndex, rows);
                if ((mainDiagonalWord.equals(MAS) || mainDiagonalWord.equals(SAM))
                        && (secondaryDiagonalWord.equals(MAS) || secondaryDiagonalWord.equals(SAM))) {
                    sum++;
                }
            }
        }

        return sum;
    }

    private String getMainDiagonalWord(int rowIndex, int columnIndex, List<String> rows) {
        return String.valueOf(rows.get(rowIndex - 1).charAt(columnIndex - 1)) +
                rows.get(rowIndex).charAt(columnIndex) +
                rows.get(rowIndex + 1).charAt(columnIndex + 1);
    }

    private String getSecondaryDiagonalWord(int rowIndex, int columnIndex, List<String> rows) {
        return String.valueOf(rows.get(rowIndex + 1).charAt(columnIndex - 1)) +
                rows.get(rowIndex).charAt(columnIndex) +
                rows.get(rowIndex - 1).charAt(columnIndex + 1);
    }
}
