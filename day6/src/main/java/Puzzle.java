import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class Puzzle {
  public static int howManyLightsAreLit(List<String> instructions) {
    return (int)
        executeInstructions(instructions.stream().map(Instruction::parse).toList())
            .filter(Light::isOn)
            .count();
  }

  public static int whatIsTheTotalBrightness(List<String> instructions) {
    return executeInstructions(instructions.stream().map(Instruction::parse).toList())
        .mapToInt(Light::getBrightness)
        .reduce(0, Integer::sum);
  }

  private static Stream<Light> executeInstructions(List<Instruction> instructions) {
    final var lights = new Light[1000][1000];
    for (int y = 0; y < 1000; y++) {
      for (int x = 0; x < 1000; x++) {
        lights[y][x] = Light.builder().isOn(false).build();
      }
    }

    for (Instruction instruction : instructions) {
      for (int y = instruction.yf; y <= instruction.yt; y++) {
        for (int x = instruction.xf; x <= instruction.xt; x++) {
          final var light = lights[y][x];
          switch (instruction.action) {
            case TURN_ON -> {
              light.setOn(true);
              light.increaseBrightness(1);
            }
            case TURN_OF -> {
              light.setOn(false);
              light.decreaseBrightness();
            }
            case TOGGLE -> {
              light.setOn(!light.isOn());
              light.increaseBrightness(2);
            }
          }
        }
      }
    }

    return Arrays.stream(lights).flatMap(Arrays::stream);
  }

  private enum Action {
    TURN_ON,
    TURN_OF,
    TOGGLE,
    ;

    static Action create(String instruction) {
      if (instruction.startsWith("turn on")) {
        return Action.TURN_ON;
      } else if (instruction.startsWith("toggle")) {
        return Action.TOGGLE;
      } else {
        return Action.TURN_OF;
      }
    }
  }

  @Builder
  @Getter
  @Setter
  private static class Light {
    private boolean isOn;
    private int brightness;

    public void increaseBrightness(int amount) {
      this.brightness += amount;
    }

    public void decreaseBrightness() {
      this.brightness = Math.max(brightness - 1, 0);
    }
  }

  @Builder
  private record Instruction(int xf, int yf, int xt, int yt, Action action) {
    static Instruction parse(String instruction) {
      final var split = instruction.split(" ");
      final String[] fromSplit;
      final String[] toSplit;
      if (split.length > 4) {
        fromSplit = split[2].split(",");
        toSplit = split[4].split(",");
      } else {
        fromSplit = split[1].split(",");
        toSplit = split[3].split(",");
      }

      return Instruction.builder()
          .xf(Integer.parseInt(fromSplit[0]))
          .yf(Integer.parseInt(fromSplit[1]))
          .xt(Integer.parseInt(toSplit[0]))
          .yt(Integer.parseInt(toSplit[1]))
          .action(Action.create(instruction))
          .build();
    }
  }
}
