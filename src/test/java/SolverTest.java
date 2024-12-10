import org.example.Solver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolverTest {

    final Solver solver = new Solver();

    @Test
    public void testInput1() {
        String fileName = "input1.txt";
        int middlePageSumForCorrectUpdates = solver.computeMiddlePageSumForCorrectUpdates(fileName);
        assertEquals(123, middlePageSumForCorrectUpdates);
    }

    @Test
    public void testInput2() {
        String fileName = "input2.txt";
        int middlePageSumForCorrectUpdates = solver.computeMiddlePageSumForCorrectUpdates(fileName);
        assertEquals(6004, middlePageSumForCorrectUpdates);
    }
}
