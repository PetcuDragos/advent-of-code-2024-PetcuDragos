import org.example.Solver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolverTest {

    final Solver solver = new Solver();

    @Test
    public void testInput1() {
        String fileName = "input1.txt";
        long checksum = solver.computeChecksum(fileName);
        assertEquals(2858, checksum);
    }

    @Test
    public void testInput2() {
        String fileName = "input2.txt";
        long checksum = solver.computeChecksum(fileName);
        assertEquals(6379677752410L, checksum);
    }
}
