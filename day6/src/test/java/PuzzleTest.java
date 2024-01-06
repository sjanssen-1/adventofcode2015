import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;

class PuzzleTest {

  @Test
  void howManyLightsAreLit_turnAllOn() {
    assertEquals(1000 * 1000, Puzzle.howManyLightsAreLit(List.of("turn on 0,0 through 999,999")));
  }

  @Test
  void howManyLightsAreLit_toggleOneLine() {
    assertEquals(1000, Puzzle.howManyLightsAreLit(List.of("toggle 0,0 through 999,0")));
  }

  @Test
  void howManyLightsAreLit_realInput() throws IOException {
    assertEquals(400410, Puzzle.howManyLightsAreLit(PuzzleHelper.readInputList("input.txt")));
  }

  @Test
  void whatIsTheTotalBrightness_shouldReturn1() {
    assertEquals(1, Puzzle.whatIsTheTotalBrightness(List.of("turn on 0,0 through 0,0")));
  }

  @Test
  void whatIsTheTotalBrightness_shouldReturn2000000() {
    assertEquals(2000000, Puzzle.whatIsTheTotalBrightness(List.of("toggle 0,0 through 999,999")));
  }

  @Test
  void whatIsTheTotalBrightness_realInput() throws IOException {
    assertEquals(
        15343601, Puzzle.whatIsTheTotalBrightness(PuzzleHelper.readInputList("input.txt")));
  }
}
