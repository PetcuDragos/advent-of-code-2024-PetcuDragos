import org.example.Solver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolverTest {

    final Solver solver = new Solver();

    @Test
    public void testInput1() {
        String fileName = "input1.txt";
        int xmasCounter = solver.computeXmasCounter(fileName);
        assertEquals(18, xmasCounter);
    }

    @Test
    public void testInput2() {
        String fileName = "input2.txt";
        int xmasCounter = solver.computeXmasCounter(fileName);
        assertEquals(2536, xmasCounter);
    }
}
