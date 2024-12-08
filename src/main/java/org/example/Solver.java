package org.example;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solver {

    public int computeNumberOfSafeReports(String inputFileName) {
        List<String> lines = FileReaderUtil.readLinesFromFile(inputFileName);

        Set<List<Integer>> reports = getReports(lines);

        int safeReportsCounter = 0;
        for (List<Integer> report : reports) {
            if (isReportSafe(report)) {
                safeReportsCounter++;
            }
        }

        return safeReportsCounter;
    }

    private boolean isReportSafe(List<Integer> report) {
        if (report.size() == 1) {
            return true;
        }

        boolean shouldBeAscending = report.get(0) < report.get(1);

        for (int index = 0; index < report.size() - 1; index++) {
            Integer currentLevel = report.get(index);
            Integer nextLevel = report.get(index + 1);
            if (shouldBeAscending && currentLevel > nextLevel) {
                return false;
            } else if (!shouldBeAscending && currentLevel < nextLevel) {
                return false;
            }
            if (!isSecondRuleMet(currentLevel, nextLevel)) {
                return false;
            }
        }
        return true;
    }

    private boolean isSecondRuleMet(Integer currentLevel, Integer nextLevel) {
        return Math.abs(nextLevel - currentLevel) > 0 &&
                Math.abs(nextLevel - currentLevel) <= 3;
    }

    private Set<List<Integer>> getReports(List<String> lines) {
        Set<List<Integer>> reports = new HashSet<>();
        for (String line : lines) {
            List<Integer> report = Arrays.stream(line.split(" ")).map(Integer::parseInt).toList();
            reports.add(report);
        }
        return reports;
    }
}
