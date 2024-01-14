import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Builder;
import org.apache.commons.collections4.iterators.PermutationIterator;

public class Puzzle {
  public static int calculateTotalHappiness(List<String> seatingParameters) {
    return calculateTotalHappiness(seatingParameters, false);
  }

  public static int calculateTotalHappiness(List<String> seatingParameters, boolean includeMyself) {
    final Set<String> attendees = new HashSet<>();
    if (includeMyself) {
      attendees.add("clydefrog");
    }
    final List<HappinessParameter> happinessParameters = new ArrayList<>();
    for (String seatingParameter : seatingParameters) {
      final var happinessParameter = HappinessParameter.parse(seatingParameter);
      attendees.add(happinessParameter.giver);
      attendees.add(happinessParameter.receiver);
      happinessParameters.add(happinessParameter);
    }

    var bestHappiness = 0;
    final var happinessEngine =
        HappinessEngine.builder().happinessParameters(happinessParameters).build();
    final var permutationIterator = new PermutationIterator<>(attendees);

    while (permutationIterator.hasNext()) {
      final var next = permutationIterator.next();
      bestHappiness = Math.max(bestHappiness, happinessEngine.applyHappinessParameters(next));
    }

    return bestHappiness;
  }

  @Builder
  static class HappinessEngine {
    public List<HappinessParameter> happinessParameters;

    int applyHappinessParameters(List<String> arrangement) {
      var totalHappiness = 0;
      for (int i = 0; i < arrangement.size(); i++) {
        var leftNeighbourIndex = i == 0 ? arrangement.size() - 1 : i - 1;
        var rightNeighbourIndex = i == arrangement.size() - 1 ? 0 : i + 1;

        var leftNeighbour = arrangement.get(leftNeighbourIndex);
        var rightNeighbour = arrangement.get(rightNeighbourIndex);
        var person = arrangement.get(i);

        totalHappiness +=
            happinessParameters.stream()
                .map(
                    happinessParameter -> {
                      if (happinessParameter.receiver.equals(person)
                          && (happinessParameter.giver.equals(leftNeighbour)
                              || happinessParameter.giver.equals(rightNeighbour))) {
                        return happinessParameter.happinessChange;
                      }
                      return 0;
                    })
                .reduce(0, Integer::sum);
      }
      return totalHappiness;
    }
  }

  @Builder
  record HappinessParameter(String receiver, String giver, int happinessChange) {
    static HappinessParameter parse(String string) {
      final var split = string.split(" ");
      final var receiver = split[0];
      final var giver = split[10].substring(0, split[10].length() - 1);
      final var change = split[2];
      final var happinessChange =
          change.equals("gain")
              ? Integer.parseInt(split[3])
              : Math.negateExact(Integer.parseInt(split[3]));

      return HappinessParameter.builder()
          .receiver(receiver)
          .giver(giver)
          .happinessChange(happinessChange)
          .build();
    }
  }
}
