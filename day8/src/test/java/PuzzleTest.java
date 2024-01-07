import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.Test;

class PuzzleTest {

  @Test
  void findStorageDifference_sample() throws IOException {
    final var storage = PuzzleHelper.readInputList("sample.txt");
    assertEquals(12, Puzzle.findStorageDifference(storage));
  }

  @Test
  void findStorageDifference_input() throws IOException {
    final var storage = PuzzleHelper.readInputList("input.txt");
    assertEquals(1371, Puzzle.findStorageDifference(storage));
  }

  @Test
  void findStorageDifferenceForEscapedStrings_sample() throws IOException {
    final var storage = PuzzleHelper.readInputList("sample.txt");
    assertEquals(19, Puzzle.findStorageDifferenceForEscapedStrings(storage));
  }

  @Test
  void findStorageDifferenceForEscapedStrings_input() throws IOException {
    final var storage = PuzzleHelper.readInputList("input.txt");
    assertEquals(2117, Puzzle.findStorageDifferenceForEscapedStrings(storage));
  }
}
