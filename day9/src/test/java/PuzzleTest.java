import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.Test;

class PuzzleTest {

  @Test
  void findShortestRoute_sample() throws IOException {
    assertEquals(605, Puzzle.findShortestRoute(PuzzleHelper.readInputList("sample.txt")));
  }

  @Test
  void findShortestRoute_input() throws IOException {
    assertEquals(117, Puzzle.findShortestRoute(PuzzleHelper.readInputList("input.txt")));
  }

  @Test
  void findLongestRoute_sample() throws IOException {
    assertEquals(982, Puzzle.findLongestRoute(PuzzleHelper.readInputList("sample.txt")));
  }

  @Test
  void findLongestRoute_input() throws IOException {
    assertEquals(909, Puzzle.findLongestRoute(PuzzleHelper.readInputList("input.txt")));
  }
}
