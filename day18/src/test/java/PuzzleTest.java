import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.Test;

class PuzzleTest {

  @Test
  void findEnabledLights_sample() throws IOException {
    assertEquals(4, Puzzle.findEnabledLights(PuzzleHelper.readInputList("sample.txt"), 4, false));
    assertEquals(17, Puzzle.findEnabledLights(PuzzleHelper.readInputList("sample.txt"), 5, true));
  }

  @Test
  void findEnabledLights_input() throws IOException {
    assertEquals(
        814, Puzzle.findEnabledLights(PuzzleHelper.readInputList("input.txt"), 100, false));
    assertEquals(924, Puzzle.findEnabledLights(PuzzleHelper.readInputList("input.txt"), 100, true));
  }
}
