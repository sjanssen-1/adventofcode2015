import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Builder;

public class Puzzle {
  private static final Map<String, Integer> tickerTape =
      Map.of(
          "children",
          3,
          "cats",
          7,
          "samoyeds",
          2,
          "pomeranians",
          3,
          "akitas",
          0,
          "vizslas",
          0,
          "goldfish",
          5,
          "trees",
          3,
          "cars",
          2,
          "perfumes",
          1);

  public static int determineAuntSue(List<String> aunts) {
    var foundAunt =
        aunts.stream()
            .map(AuntSue::parse)
            .filter(
                auntSue -> {
                  for (var compound : auntSue.compounds.entrySet()) {
                    if (!tickerTape.containsKey(compound.getKey())
                        || !tickerTape.get(compound.getKey()).equals(compound.getValue())) {
                      return false;
                    }
                  }
                  return true;
                })
            .findFirst();

    return foundAunt.orElseThrow().id;
  }

  public static int determineRealAuntSue(List<String> aunts) {
    var foundAunt =
        aunts.stream()
            .map(AuntSue::parse)
            .filter(
                auntSue -> {
                  for (var compound : auntSue.compounds.entrySet()) {
                    if (compound.getKey().equals("cats") || compound.getKey().equals("trees")) {
                      if (compound.getValue() <= tickerTape.get(compound.getKey())) {
                        return false;
                      }
                      continue;
                    }

                    if (compound.getKey().equals("pomeranians")
                        || compound.getKey().equals("goldfish")) {
                      if (compound.getValue() >= tickerTape.get(compound.getKey())) {
                        return false;
                      }
                      continue;
                    }

                    if (!tickerTape.containsKey(compound.getKey())
                        || !tickerTape.get(compound.getKey()).equals(compound.getValue())) {
                      return false;
                    }
                  }
                  return true;
                })
            .findFirst();

    return foundAunt.orElseThrow().id;
  }

  @Builder
  record AuntSue(int id, Map<String, Integer> compounds) {
    static AuntSue parse(String string) {
      var split = string.split(": ", 2);
      var auntId = Integer.parseInt(split[0].split(" ")[1]);
      var compounds =
          Arrays.stream(split[1].split(", "))
              .map(s -> s.split(": "))
              .collect(Collectors.toMap(s -> s[0], s -> Integer.parseInt(s[1])));

      return AuntSue.builder().id(auntId).compounds(compounds).build();
    }
  }
}
