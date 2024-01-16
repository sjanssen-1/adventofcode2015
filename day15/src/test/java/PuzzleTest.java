import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.Test;

class PuzzleTest {

  @Test
  void findBestScoringCookie_sample() throws IOException {
    assertEquals(
        62842880, Puzzle.findBestScoringCookie(PuzzleHelper.readInputList("sample.txt"), false));
    assertEquals(
        57600000, Puzzle.findBestScoringCookie(PuzzleHelper.readInputList("sample.txt"), true));
  }

  @Test
  void findBestScoringCookie_input() throws IOException {
    assertEquals(
        21367368, Puzzle.findBestScoringCookie(PuzzleHelper.readInputList("input.txt"), false));
    assertEquals(
        1766400, Puzzle.findBestScoringCookie(PuzzleHelper.readInputList("input.txt"), true));
  }
}
