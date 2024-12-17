import org.example.Solver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolverTest {

    final Solver solver = new Solver();

    @Test
    public void testInput1() {
        String fileName = "input1.txt";
        long numberOfTokens = solver.computeNumberOfTokens(fileName);
        assertEquals(480, numberOfTokens);
    }

    @Test
    public void testInput2() {
        String fileName = "input2.txt";
        long numberOfTokens = solver.computeNumberOfTokens(fileName);
        assertEquals(39996, numberOfTokens);
    }
}
