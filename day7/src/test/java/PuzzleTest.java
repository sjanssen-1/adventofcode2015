import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;

class PuzzleTest {

  @Test
  void findSignalForWire_sample() throws IOException {
    List<String> instructions = PuzzleHelper.readInputList("sample.txt");
    assertEquals(72, Puzzle.findSignalForWire(instructions, "d"));
    assertEquals(507, Puzzle.findSignalForWire(instructions, "e"));
    assertEquals(492, Puzzle.findSignalForWire(instructions, "f"));
    assertEquals(114, Puzzle.findSignalForWire(instructions, "g"));
    assertEquals(65412, Puzzle.findSignalForWire(instructions, "h"));
    assertEquals(65079, Puzzle.findSignalForWire(instructions, "i"));
    assertEquals(123, Puzzle.findSignalForWire(instructions, "x"));
    assertEquals(456, Puzzle.findSignalForWire(instructions, "y"));
  }

  @Test
  void findSignalForWire_input() throws IOException {
    List<String> instructions = PuzzleHelper.readInputList("input.txt");
    var wireA = Puzzle.findSignalForWire(instructions, "a");
    assertEquals(16076, wireA);
  }
}
