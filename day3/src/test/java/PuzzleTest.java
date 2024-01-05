import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.Test;

class PuzzleTest {

  @Test
  void findAmountOfHousesWithAtLeastOnePresent_shouldReturn2() {
    assertEquals(2, Puzzle.findAmountOfHousesWithAtLeastOnePresent(">"));
    assertEquals(2, Puzzle.findAmountOfHousesWithAtLeastOnePresent("^v^v^v^v^v"));
  }

  @Test
  void findAmountOfHousesWithAtLeastOnePresent_shouldReturn4() {
    assertEquals(4, Puzzle.findAmountOfHousesWithAtLeastOnePresent("^>v<"));
  }

  @Test
  void findAmountOfHousesWithAtLeastOnePresent_realInput() throws IOException {
    assertEquals(
        2572,
        Puzzle.findAmountOfHousesWithAtLeastOnePresent(PuzzleHelper.readInputString("input.txt")));
  }

  @Test
  void findAmountOfHousesWithAtLeastOnePresentWithRoboSanta_shouldReturn3() {
    assertEquals(3, Puzzle.findAmountOfHousesWithAtLeastOnePresentWithRoboSanta("^v"));
    assertEquals(3, Puzzle.findAmountOfHousesWithAtLeastOnePresentWithRoboSanta("^>v<"));
  }

  @Test
  void findAmountOfHousesWithAtLeastOnePresentWithRoboSanta_shouldReturn11() {
    assertEquals(11, Puzzle.findAmountOfHousesWithAtLeastOnePresentWithRoboSanta("^v^v^v^v^v"));
  }

  @Test
  void findAmountOfHousesWithAtLeastOnePresentWithRoboSanta_realInput() throws IOException {
    assertEquals(
        2631,
        Puzzle.findAmountOfHousesWithAtLeastOnePresentWithRoboSanta(
            PuzzleHelper.readInputString("input.txt")));
  }
}
