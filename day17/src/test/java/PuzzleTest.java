import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;

class PuzzleTest {

  @Test
  void findEggnogCombinations_sample() {
    assertEquals(4, Puzzle.findEggnogCombinations(List.of("20", "15", "10", "5", "5"), 25));
  }

  @Test
  void findMinimumEggnogCombinations_sample() {
    assertEquals(3, Puzzle.findMinimumEggnogCombinations(List.of("20", "15", "10", "5", "5"), 25));
  }

  @Test
  void findEggnogCombinations_input() throws IOException {
    assertEquals(1304, Puzzle.findEggnogCombinations(PuzzleHelper.readInputList("input.txt"), 150));
  }

  @Test
  void findMinimumEggnogCombinations_input() throws IOException {
    assertEquals(
        18, Puzzle.findMinimumEggnogCombinations(PuzzleHelper.readInputList("input.txt"), 150));
  }
}
