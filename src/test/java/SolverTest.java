import org.example.Solver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolverTest {

    final Solver solver = new Solver();

    @Test
    public void testInput1() {
        String fileName = "input1.txt";
        int multiplicationSum = solver.computeMultiplicationSum(fileName);
        assertEquals(48, multiplicationSum);
    }

    @Test
    public void testInput2() {
        String fileName = "input2.txt";
        int multiplicationSum = solver.computeMultiplicationSum(fileName);
        assertEquals(74838033, multiplicationSum);
    }
}
