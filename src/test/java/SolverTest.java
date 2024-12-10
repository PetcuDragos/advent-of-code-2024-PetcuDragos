import org.example.Solver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolverTest {

    final Solver solver = new Solver();

    @Test
    public void testInput1() {
        String fileName = "input1.txt";
        int distinctPositionsCounter = solver.computeDistinctPositionsCounter(fileName);
        assertEquals(41, distinctPositionsCounter);
    }

    @Test
    public void testInput2() {
        String fileName = "input2.txt";
        int distinctPositionsCounter = solver.computeDistinctPositionsCounter(fileName);
        assertEquals(5453, distinctPositionsCounter);
    }
}
