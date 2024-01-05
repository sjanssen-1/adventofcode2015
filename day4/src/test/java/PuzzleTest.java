import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PuzzleTest {

  @Test
  void findHash_forSecretabcdefShouldReturn609043() {
    assertEquals(609043, Puzzle.findHash("abcdef", "00000"));
  }

  @Test
  void findHash_forSecretpqrstuvShouldReturn1048970() {
    assertEquals(1048970, Puzzle.findHash("pqrstuv", "00000"));
  }

  @Test
  void findHash_realInputFiveZeroes() {
    assertEquals(117946, Puzzle.findHash("ckczppom", "00000"));
  }

  @Test
  void findHash_realInputSixZeroes() {
    assertEquals(3938038, Puzzle.findHash("ckczppom", "000000"));
  }
}
