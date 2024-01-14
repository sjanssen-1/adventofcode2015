import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.Test;

class PuzzleTest {

  @Test
  void findSum_sample() throws IOException {
    assertEquals(6, Puzzle.findSum("[1,2,3]", false));
    assertEquals(6, Puzzle.findSum("{\"a\":2,\"b\":4}", false));
    assertEquals(3, Puzzle.findSum("[[[3]]]", false));
    assertEquals(3, Puzzle.findSum("{\"a\":{\"b\":4},\"c\":-1}", false));
    assertEquals(0, Puzzle.findSum("{\"a\":[-1,1]}", false));
    assertEquals(0, Puzzle.findSum("[-1,{\"a\":1}]", false));
    assertEquals(0, Puzzle.findSum("[]", false));
    assertEquals(0, Puzzle.findSum("{}", false));

    assertEquals(6, Puzzle.findSum("[1,2,3]", true));
    assertEquals(4, Puzzle.findSum("[1,{\"c\":\"red\",\"b\":2},3]", true));
    assertEquals(0, Puzzle.findSum("{\"d\":\"red\",\"e\":[1,2,3,4],\"f\":5}", true));
    assertEquals(6, Puzzle.findSum("[1,\"red\",5]", true));
  }

  @Test
  void findSum_input() throws IOException {
    assertEquals(191164, Puzzle.findSum(PuzzleHelper.readInputString("input.txt"), false));

    assertEquals(87842, Puzzle.findSum(PuzzleHelper.readInputString("input.txt"), true));
  }
}
