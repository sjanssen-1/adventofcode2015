import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.Test;

class PuzzleTest {

  @Test
  void calculateTotalHappiness_sample() throws IOException {
    assertEquals(330, Puzzle.calculateTotalHappiness(PuzzleHelper.readInputList("sample.txt")));
  }

  @Test
  void calculateTotalHappiness_input() throws IOException {
    assertEquals(709, Puzzle.calculateTotalHappiness(PuzzleHelper.readInputList("input.txt")));
    assertEquals(
        668, Puzzle.calculateTotalHappiness(PuzzleHelper.readInputList("input.txt"), true));
  }
}
