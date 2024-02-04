import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.Test;

class PuzzleTest {

  @Test
  void findDistinctMolecules_sample() throws IOException {
    assertEquals(4, Puzzle.findDistinctMolecules(PuzzleHelper.readInputList("sample.txt")));
  }

  @Test
  void findDistinctMolecules_input() throws IOException {
    assertEquals(535, Puzzle.findDistinctMolecules(PuzzleHelper.readInputList("input.txt")));
  }
}
