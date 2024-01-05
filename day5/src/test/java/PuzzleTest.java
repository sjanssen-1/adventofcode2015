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

  @Test
  void isNiceStringButLessRidiculous_shouldReturnTrue() {
    assertTrue(Puzzle.isNiceStringButLessRidiculous("qjhvhtzxzqqjkmpb"));
    assertTrue(Puzzle.isNiceStringButLessRidiculous("xxyxx"));
  }

  @Test
  void isNiceStringButLessRidiculous_shouldReturnFalse() {
    assertFalse(Puzzle.isNiceStringButLessRidiculous("uurcxstgmygtbstg"));
    assertFalse(Puzzle.isNiceStringButLessRidiculous("ieodomkazucvgmuy"));
  }

  @Test
  void isNiceStringButLessRidiculous_realInput() throws IOException {
    assertEquals(
        53,
        PuzzleHelper.readInputList("input.txt").stream()
            .map(Puzzle::isNiceStringButLessRidiculous)
            .filter(aBoolean -> aBoolean)
            .count());
  }
}
