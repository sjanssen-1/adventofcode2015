import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.Test;

class PuzzleTest {
  @Test
  void findFloor_shouldReturn0() {
    assertEquals(0, Puzzle.findFloor("(())"));
    assertEquals(0, Puzzle.findFloor("()()"));
  }

  @Test
  void findFloor_shouldReturn3() {
    assertEquals(3, Puzzle.findFloor("((("));
    assertEquals(3, Puzzle.findFloor("(()(()("));
    assertEquals(3, Puzzle.findFloor("))((((("));
  }

  @Test
  void findFloor_shouldReturnNegative1() {
    assertEquals(-1, Puzzle.findFloor("())"));
    assertEquals(-1, Puzzle.findFloor("))("));
  }

  @Test
  void findFloor_shouldReturnNegative3() {
    assertEquals(-3, Puzzle.findFloor(")))"));
    assertEquals(-3, Puzzle.findFloor(")())())"));
  }

  @Test
  void findFloor_realInput() throws IOException {
    final String instructions = PuzzleHelper.readInputString("input.txt");
    assertEquals(74, Puzzle.findFloor(instructions));
  }

  @Test
  void findBasement_shouldReturn1() {
    assertEquals(1, Puzzle.findBasement(")"));
  }

  @Test
  void findBasement_shouldReturn5() {
    assertEquals(5, Puzzle.findBasement("()())"));
  }

  @Test
  void findBasement_realInput() throws IOException {
    final String instructions = PuzzleHelper.readInputString("input.txt");
    assertEquals(1795, Puzzle.findBasement(instructions));
  }
}
