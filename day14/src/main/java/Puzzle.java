import java.util.*;
import lombok.Builder;

public class Puzzle {
  public static int calculateWinningDistance(List<String> reindeer, int duration) {
    var distance =
        reindeer.stream()
            .map(Reindeer::parse)
            .map(r -> r.calculateDistance(duration))
            .max(Comparator.naturalOrder());

    if (distance.isPresent()) {
      return distance.get();
    }
    throw new RuntimeException();
  }

  public static int calculateWinningScore(List<String> reindeer, int duration) {
    final List<Reindeer> reindeerList = reindeer.stream().map(Reindeer::parse).toList();

    final var scores = new HashMap<String, Integer>();
    reindeerList.forEach(r -> scores.put(r.name, 0));

    Set<String> winningReindeer = new HashSet<>();
    int mostDistance = 0;
    for (int i = 0; i < duration; i++) {
      for (Reindeer r : reindeerList) {
        final int rDistance = r.calculateDistance(i + 1);
        if (rDistance > mostDistance) {
          mostDistance = rDistance;
          winningReindeer.clear();
          winningReindeer.add(r.name);
        } else if (rDistance == mostDistance) {
          winningReindeer.add(r.name);
        }
      }
      winningReindeer.forEach(name -> scores.put(name, scores.get(name) + 1));
    }

    return scores.values().stream().max(Comparator.naturalOrder()).get();
  }

  @Builder
  record Reindeer(String name, int speed, int activeTime, int restingTime) {
    static Reindeer parse(String string) {
      final var split = string.split(" ");
      return Reindeer.builder()
          .name(split[0])
          .speed(Integer.parseInt(split[3]))
          .activeTime(Integer.parseInt(split[6]))
          .restingTime(Integer.parseInt(split[13]))
          .build();
    }

    int calculateDistance(int duration) {
      final int totalMovingTime = (duration / (activeTime + restingTime));
      final int remainingTime = (duration % (activeTime + restingTime));

      int distance = totalMovingTime * activeTime * speed;
      if (remainingTime >= activeTime) {
        distance += activeTime * speed;
      } else {
        distance += remainingTime * speed;
      }
      return distance;
    }
  }
}
