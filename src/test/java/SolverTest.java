import org.example.Solver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolverTest {

    final Solver solver = new Solver();

    @Test
    public void testInput1() {
        String fileName = "input1.txt";
        int distance = solver.computeDistance(fileName);
        assertEquals(11, distance);
    }

    @Test
    public void testInput2() {
        String fileName = "input2.txt";
        int distance = solver.computeDistance(fileName);
        assertEquals(1319616, distance);
    }
}
