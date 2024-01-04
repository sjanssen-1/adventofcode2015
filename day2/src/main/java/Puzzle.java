import java.util.List;

public class Puzzle {
  public static int calculateSquareFeetOfWrappingPaper(List<Present> presents) {
    return presents.stream().mapToInt(Present::calculateWrappingPaper).reduce(0, Integer::sum);
  }

  public static int calculateRibbonLength(List<Present> presents) {
    return presents.stream().mapToInt(Present::calculateRibbon).reduce(0, Integer::sum);
  }
}
