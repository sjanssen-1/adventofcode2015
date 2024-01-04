public class Puzzle {
  public static int findFloor(String instructions) {
    return instructions
        .chars()
        .reduce(
            0,
            (acc, character) -> {
              if (character == '(') {
                return acc + 1;
              } else {
                return acc - 1;
              }
            });
  }

  public static int findBasement(String instructions) {
    var currentFloor = 0;
    var currentIndex = 0;
    for (final int character : instructions.chars().toArray()) {
      if (character == '(') {
        currentFloor++;
      } else {
        currentFloor--;
      }

      currentIndex++;
      if (currentFloor == -1) {
        return currentIndex;
      }
    }
    throw new RuntimeException("Instructions never reach the basement.");
  }
}
