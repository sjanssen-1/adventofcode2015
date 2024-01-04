import java.util.List;

public class Puzzle {
  public static int calculateSquareFeetOfWrappingPaper(List<Present> present) {
    return present.stream().mapToInt(Present::calculateWrappingPaper).reduce(0, Integer::sum);
  }
}
