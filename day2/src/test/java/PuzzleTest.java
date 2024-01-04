import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;

class PuzzleTest {

  @Test
  void calculateSquareFeetOfWrappingPaper_shouldReturn58() {
    assertEquals(58, Puzzle.calculateSquareFeetOfWrappingPaper(List.of(Present.parse("2x3x4"))));
  }

  @Test
  void calculateSquareFeetOfWrappingPaper_shouldReturn43() {
    assertEquals(43, Puzzle.calculateSquareFeetOfWrappingPaper(List.of(Present.parse("1x1x10"))));
  }

  @Test
  void calculateSquareFeetOfWrappingPaper_realInput() throws IOException {
    var listOfDimensions = PuzzleHelper.readInputList("input.txt");
    var presents = listOfDimensions.stream().map(Present::parse).toList();
    assertEquals(1588178, Puzzle.calculateSquareFeetOfWrappingPaper(presents));
  }
}
