import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PuzzleTest {

  @Test
  void lookAndSay_sample() {
    assertEquals("11", Puzzle.lookAndSay("1"));
    assertEquals("21", Puzzle.lookAndSay("11"));
    assertEquals("1211", Puzzle.lookAndSay("21"));
    assertEquals("111221", Puzzle.lookAndSay("1211"));
    assertEquals("312211", Puzzle.lookAndSay("111221"));
  }

  @Test
  void lookAndSay_input40() {
    String input = "1321131112";
    for (int i = 0; i < 40; i++) {
      input = Puzzle.lookAndSay(input);
    }
    assertEquals(492982, input.length());
  }

  @Test
  void lookAndSay_input50() {
    String input = "1321131112";
    for (int i = 0; i < 50; i++) {
      input = Puzzle.lookAndSay(input);
    }
    assertEquals(6989950, input.length());
  }
}
