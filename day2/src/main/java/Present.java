import java.util.Arrays;

public record Present(int l, int w, int h) {
  public static Present parse(String s) {
    var split = s.split("x");
    return new Present(
        Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
  }

  public int calculateWrappingPaper() {
    var smallestSide = Math.min(l * w, Math.min(w * h, h * l));
    return (2 * l * w) + (2 * w * h) + (2 * h * l) + smallestSide;
  }

  public int calculateRibbon() {
    var sides = new int[] {l, w, h};
    Arrays.sort(sides);
    return l * w * h + (sides[0] * 2 + sides[1] * 2);
  }
}
