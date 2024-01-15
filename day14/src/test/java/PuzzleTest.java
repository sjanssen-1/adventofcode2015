import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.Test;

class PuzzleTest {

  @Test
  void calculateWinningDistance_sample() throws IOException {
    assertEquals(
        1120, Puzzle.calculateWinningDistance(PuzzleHelper.readInputList("sample.txt"), 1000));
  }

  @Test
  void calculateWinningDistance_input() throws IOException {
    assertEquals(
        2640, Puzzle.calculateWinningDistance(PuzzleHelper.readInputList("input.txt"), 2503));
  }

  @Test
  void calculateWinningScore_sample() throws IOException {
    assertEquals(689, Puzzle.calculateWinningScore(PuzzleHelper.readInputList("sample.txt"), 1000));
  }

  @Test
  void calculateWinningScore_input() throws IOException {
    assertEquals(1102, Puzzle.calculateWinningScore(PuzzleHelper.readInputList("input.txt"), 2503));
  }
}
