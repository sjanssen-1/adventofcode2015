import java.util.HashSet;

public class Puzzle {
  public static int findAmountOfHousesWithAtLeastOnePresent(String instructions) {
    int x = 0;
    int y = 0;
    final var visitedHouses = new HashSet<House>();
    visitedHouses.add(new House(x, y));
    for (char instruction : instructions.toCharArray()) {
      switch (instruction) {
        case '^':
          y--;
          break;
        case 'v':
          y++;
          break;
        case '>':
          x++;
          break;
        case '<':
          x--;
          break;
      }
      visitedHouses.add(new House(x, y));
    }
    return visitedHouses.size();
  }

  public static int findAmountOfHousesWithAtLeastOnePresentWithRoboSanta(String instructions) {
    int xSanta = 0;
    int ySanta = 0;

    int xRoboSanta = 0;
    int yRoboSanta = 0;

    final var visitedHouses = new HashSet<House>();
    visitedHouses.add(new House(xSanta, ySanta));

    final var instructionsArray = instructions.toCharArray();
    for (int i = 0; i < instructionsArray.length; i++) {
      int x = 0;
      int y = 0;
      switch (instructionsArray[i]) {
        case '^':
          y--;
          break;
        case 'v':
          y++;
          break;
        case '>':
          x++;
          break;
        case '<':
          x--;
          break;
      }
      if (i % 2 == 0) {
        xSanta += x;
        ySanta += y;
        visitedHouses.add(new House(xSanta, ySanta));
      } else {
        xRoboSanta += x;
        yRoboSanta += y;
        visitedHouses.add(new House(xRoboSanta, yRoboSanta));
      }
    }
    return visitedHouses.size();
  }

  private record House(int x, int y) {}
}
