import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.Test;

class PuzzleTest {

  @Test
  void isNiceString_shouldReturnTrue() {
    assertTrue(Puzzle.isNiceString("ugknbfddgicrmopn"));
    assertTrue(Puzzle.isNiceString("aaa"));
  }

  @Test
  void isNiceString_shouldReturnFalse() {
    assertFalse(Puzzle.isNiceString("jchzalrnumimnmhp"));
    assertFalse(Puzzle.isNiceString("haegwjzuvuyypxyu"));
    assertFalse(Puzzle.isNiceString("dvszwmarrgswjxmb"));
  }

  @Test
  void isNiceString_realInput() throws IOException {
    assertEquals(
        258,
        PuzzleHelper.readInputList("input.txt").stream()
            .map(Puzzle::isNiceString)
            .filter(aBoolean -> aBoolean)
            .count());
  }
}
