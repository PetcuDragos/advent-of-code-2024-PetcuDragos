package org.example;

import java.util.*;

public class Solver {

    public int computeNumberOfSafeDampedReports(String inputFileName) {
        List<String> lines = FileReaderUtil.readLinesFromFile(inputFileName);

        Set<List<Integer>> reports = getReports(lines);

        int safeDampedReportsCounter = 0;
        for (List<Integer> report : reports) {
            if (isDampedReportSafe(report, true)) {
                safeDampedReportsCounter++;
            }
        }

        return safeDampedReportsCounter;
    }

    private boolean isDampedReportSafe(List<Integer> report, boolean acceptDamping) {
        if (report.size() == 1) {
            return true;
        }

        boolean shouldBeAscending = report.get(0) < report.get(1);

        for (int index = 0; index < report.size() - 1; index++) {
            Integer currentLevel = report.get(index);
            Integer nextLevel = report.get(index + 1);
            if (shouldBeAscending && currentLevel > nextLevel) {
                return checkIfDampedReportIsSafe(report, acceptDamping, index);
            } else if (!shouldBeAscending && currentLevel < nextLevel) {
                return checkIfDampedReportIsSafe(report, acceptDamping, index);
            }
            if (!isSecondRuleMet(currentLevel, nextLevel)) {
                return checkIfDampedReportIsSafe(report, acceptDamping, index);
            }
        }
        return true;
    }

    private boolean checkIfDampedReportIsSafe(List<Integer> report, boolean acceptDamping, int index) {
        if (!acceptDamping) {
            return false;
        }
        List<Integer> reportWithoutCurrentLevel = filterReportWithoutIndex(report, index);
        if (isDampedReportSafe(reportWithoutCurrentLevel, false)) return true;
        List<Integer> reportWithoutPreviousLevel = filterReportWithoutIndex(report, index - 1);
        if (isDampedReportSafe(reportWithoutPreviousLevel, false)) return true;
        List<Integer> reportWithoutNextLevel = filterReportWithoutIndex(report, index + 1);
        if (isDampedReportSafe(reportWithoutNextLevel, false)) return true;
        return false;
    }

    private List<Integer> filterReportWithoutIndex(List<Integer> report, int index) {
        List<Integer> filteredReport = new ArrayList<>(report.subList(0, Math.max(0, index)));
        filteredReport.addAll(report.subList(Math.min(index + 1, report.size()), report.size()));
        return filteredReport;
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
