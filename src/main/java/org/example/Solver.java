package org.example;

import java.util.*;

public class Solver {

    public int computeMiddlePageSumForCorrectUpdates(String inputFileName) {
        List<String> lines = FileReaderUtil.readLinesFromFile(inputFileName);
        List<Pair<Integer, Integer>> orderingRules = getOrderingRules(lines);
        List<List<Integer>> updates = getUpdates(lines);

        int middlePageSumForCorrectUpdates = 0;

        for (List<Integer> update : updates) {
            Map<Integer, List<Integer>> parentToChildren = new HashMap<>();
            Map<Integer, Integer> numberToWeight = new HashMap<>();
            processOrderingRulesForUpdate(orderingRules, update, parentToChildren, numberToWeight);

            if (!isUpdateOrderedCorrectly(update, numberToWeight)) {
                List<Integer> sortedUpdate = update.stream()
                        .sorted(Comparator.comparingInt(numberToWeight::get)).toList();
                middlePageSumForCorrectUpdates += getMiddlePageNumber(sortedUpdate);
            }
        }

        return middlePageSumForCorrectUpdates;
    }

    private boolean isUpdateOrderedCorrectly(List<Integer> update, Map<Integer, Integer> numberToWeight) {
        Integer lastWeight = null;
        for (Integer page : update) {
            if (!numberToWeight.containsKey(page)) {
                continue;
            }

            if (lastWeight == null || numberToWeight.get(page) <= lastWeight) {
                lastWeight = numberToWeight.get(page);
            } else return false;
        }
        return true;
    }

    private void processOrderingRulesForUpdate(List<Pair<Integer, Integer>> orderingRules, List<Integer> update,
                                               Map<Integer, List<Integer>> parentToChildren,
                                               Map<Integer, Integer> numberToWeight) {

        List<Pair<Integer, Integer>> filteredRulesForUpdate = orderingRules.stream()
                .filter(rule -> update.contains(rule.first) && update.contains(rule.second)).toList();

        for (Pair<Integer, Integer> orderingRule : filteredRulesForUpdate) {
            int parent = orderingRule.first;
            int newChild = orderingRule.second;

            // did this in case of stack overflow
//            changeWeightIteratively(parent, newChild, parentToChildren, numberToWeight);

            changeWeightRecursively(parent, newChild, parentToChildren, numberToWeight);
            parentToChildren.putIfAbsent(parent, new ArrayList<>());
            parentToChildren.get(parent).add(newChild);
        }
    }

    private void changeWeightRecursively(int parent, int child, Map<Integer, List<Integer>> parentToChildren,
                                         Map<Integer, Integer> numberToWeight) {
        int parentWeight = getWeight(parent, numberToWeight);
        int childWeight = getWeight(child, numberToWeight);

        if (parentWeight - 1 < childWeight) {
            numberToWeight.put(child, parentWeight - 1);

            if (!parentToChildren.containsKey(child)) {
                return;
            }

            for (Integer childOfChild : parentToChildren.get(child)) {
                changeWeightRecursively(child, childOfChild, parentToChildren, numberToWeight);
            }
        }
    }


    private int getMiddlePageNumber(List<Integer> update) {
        return update.get(update.size() / 2);
    }

    private List<List<Integer>> getUpdates(List<String> lines) {
        List<List<Integer>> updates = new ArrayList<>();
        for (String line : lines) {
            if (!line.contains("|") && !line.isEmpty() && !line.isBlank()) {
                String[] numbers = line.split(",");
                List<Integer> update = Arrays.stream(numbers).map(Integer::parseInt).toList();
                updates.add(update);
            }
        }
        return updates;
    }

    private List<Pair<Integer, Integer>> getOrderingRules(List<String> lines) {
        List<Pair<Integer, Integer>> orderingRules = new ArrayList<>();
        for (String line : lines) {
            if (line.contains("|")) {
                String[] numbers = line.split("\\|");
                orderingRules.add(new Pair<>(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1])));
            } else {
                break;
            }
        }
        return orderingRules;
    }

    //    private void changeWeightIteratively(int initialParent, int initialChild, Map<Integer, List<Integer>> parentToChildren,
//                                         Map<Integer, Integer> numberToWeight) {
//        Queue<Pair<Integer, Integer>> parentChildQueue = new ArrayDeque<>();
//        // using queue in order to not use recursive function as it would result in stack overflow.
//        parentChildQueue.add(new Pair<>(initialParent, initialChild));
//
//        while (!parentChildQueue.isEmpty()) {
//            Pair<Integer, Integer> parentChild = parentChildQueue.remove();
//            int parent = parentChild.first;
//            int child = parentChild.second;
//            System.out.println(parent + "," + child);
//            int parentWeight = getWeight(parent, numberToWeight);
//            int childWeight = getWeight(child, numberToWeight);
//
//            if (parentWeight - 1 < childWeight) {
//                numberToWeight.put(child, parentWeight - 1);
//
//                if (!parentToChildren.containsKey(child)) {
//                    continue;
//                }
//
//                for (Integer childOfChild : parentToChildren.get(child)) {
//                    parentChildQueue.add(new Pair<>(child, childOfChild));
//                }
//            }
//        }
//    }
    private Integer getWeight(int number, Map<Integer, Integer> numberToWeight) {
        if (!numberToWeight.containsKey(number)) {
            numberToWeight.put(number, 0);
        }
        return numberToWeight.get(number);
    }
}
