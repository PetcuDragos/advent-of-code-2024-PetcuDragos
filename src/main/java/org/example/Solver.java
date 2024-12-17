package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solver {

    private final static int MAX_NR_OF_ALLOWED_PRESSES = 100;
    private final static int TOKEN_SPENT_FOR_A_BUTTON = 3;
    private final static int TOKEN_SPENT_FOR_B_BUTTON = 1;

    public long computeNumberOfTokens(String inputFileName) {
        List<String> lines = FileReaderUtil.readLinesFromFile(inputFileName);
        List<ClawMachine> clawMachines = getClawMachines(lines);

        long result = 0;
        for (ClawMachine clawMachine : clawMachines) {
            result += calculateOptimalSolution(clawMachine);
        }

        return result;
    }

    private long calculateOptimalSolution(ClawMachine clawMachine) {
        int minNumberOfTokens = 0;
        for (int numberOfAButtonPresses = 0; numberOfAButtonPresses <= MAX_NR_OF_ALLOWED_PRESSES; numberOfAButtonPresses++) {
            int valueXAfterAPressed = clawMachine.buttonAX() * numberOfAButtonPresses;
            int valueYAfterAPressed = clawMachine.buttonAY() * numberOfAButtonPresses;

            int numberOfBButtonPresses = (clawMachine.prizeX() - valueXAfterAPressed) / clawMachine.buttonBX();

            int valueXAfterBPressed = clawMachine.buttonBX() * numberOfBButtonPresses;
            int valueYAfterBPressed = clawMachine.buttonBY() * numberOfBButtonPresses;

            if (numberOfBButtonPresses > MAX_NR_OF_ALLOWED_PRESSES ||
                    valueXAfterAPressed + valueXAfterBPressed != clawMachine.prizeX() ||
                    valueYAfterAPressed + valueYAfterBPressed != clawMachine.prizeY()) {
                continue;
            }

            int tokensSpent = numberOfAButtonPresses * TOKEN_SPENT_FOR_A_BUTTON + numberOfBButtonPresses * TOKEN_SPENT_FOR_B_BUTTON;
            if (minNumberOfTokens == 0 || tokensSpent < minNumberOfTokens) {
                minNumberOfTokens = tokensSpent;
            }
        }
        return minNumberOfTokens;
    }

    private List<ClawMachine> getClawMachines(List<String> lines) {
        List<ClawMachine> clawMachines = new ArrayList<>();
        for (int lineIndex = 0; lineIndex < lines.size(); lineIndex += 4) {
            ClawMachine clawMachine = getClawMachine(lines.get(lineIndex), lines.get(lineIndex + 1), lines.get(lineIndex + 2));
            clawMachines.add(clawMachine);
        }
        return clawMachines;
    }

    public ClawMachine getClawMachine(String buttonAString, String buttonBString, String prizeString) {
        Integer[] buttonAValues = Arrays.stream(buttonAString.replace("Button A: X+", "")
                .replace(" Y+", "").split(",")).map(Integer::parseInt).toArray(Integer[]::new);
        Integer[] buttonBValues = Arrays.stream(buttonBString.replace("Button B: X+", "")
                .replace(" Y+", "").split(",")).map(Integer::parseInt).toArray(Integer[]::new);
        Integer[] prizeValue = Arrays.stream(prizeString.replace("Prize: X=", "")
                .replace(" Y=", "").split(",")).map(Integer::parseInt).toArray(Integer[]::new);
        return new ClawMachine(buttonAValues[0], buttonAValues[1], buttonBValues[0], buttonBValues[1], prizeValue[0], prizeValue[1]);
    }
}
