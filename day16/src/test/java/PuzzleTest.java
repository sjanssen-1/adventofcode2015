import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.Test;

class PuzzleTest {

  @Test
  void determineAuntSue() throws IOException {
    assertEquals(103, Puzzle.determineAuntSue(PuzzleHelper.readInputList("input.txt")));
    assertEquals(405, Puzzle.determineRealAuntSue(PuzzleHelper.readInputList("input.txt")));
  }
}
