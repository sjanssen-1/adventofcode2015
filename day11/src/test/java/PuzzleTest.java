import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PuzzleTest {

  @Test
  void isValidPassword_sample() {
    assertFalse(Puzzle.isValidPassword("hijklmmn"));
    assertFalse(Puzzle.isValidPassword("abbceffg"));
    assertFalse(Puzzle.isValidPassword("abbcegjk"));
    assertTrue(Puzzle.isValidPassword("abcdffaa"));
    assertTrue(Puzzle.isValidPassword("ghjaabcc"));
  }

  @Test
  void calculateNextPassword_sample() {
    assertEquals("xy", Puzzle.calculateNextPassword("xx"));
    assertEquals("xz", Puzzle.calculateNextPassword("xy"));
    assertEquals("ya", Puzzle.calculateNextPassword("xz"));
    assertEquals("yb", Puzzle.calculateNextPassword("ya"));
    assertEquals("aaa", Puzzle.calculateNextPassword("zz"));
  }

  @Test
  void calculateNextValidPassword_sample() {
    assertEquals("abcdffaa", Puzzle.calculateNextValidPassword("abcdefgh"));
    assertEquals("ghjaabcc", Puzzle.calculateNextValidPassword("ghijklmn"));
  }

  @Test
  void calculateNextValidPassword_input() {
    assertEquals("vzbxxyzz", Puzzle.calculateNextValidPassword("vzbxkghb"));
    assertEquals("vzcaabcc", Puzzle.calculateNextValidPassword("vzbxxyzz"));
  }
}
