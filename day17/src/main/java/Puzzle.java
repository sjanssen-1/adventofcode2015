import java.util.*;

public class Puzzle {
  public static int findEggnogCombinations(List<String> containers, int amountOfEggnog) {
    final var eggnogContainers = containers.stream().map(Integer::parseInt).toList();
    final var combinations = generateCombinations(eggnogContainers);

    return combinations.stream()
        .filter(integers -> integers.stream().reduce(Integer::sum).orElse(-1) == amountOfEggnog)
        .toList()
        .size();
  }

  public static int findMinimumEggnogCombinations(List<String> containers, int amountOfEggnog) {
    final var eggnogContainers = containers.stream().map(Integer::parseInt).toList();
    final var combinations = generateCombinations(eggnogContainers);

    final var validCombinations =
        combinations.stream()
            .filter(integers -> integers.stream().reduce(Integer::sum).orElse(-1) == amountOfEggnog)
            .toList();

    int min = Integer.MAX_VALUE;
    int amount = 0;

    for (List<Integer> validCombination : validCombinations) {
      if (validCombination.size() < min) {
        min = validCombination.size();
        amount = 1;
      } else if (validCombination.size() == min) {
        amount++;
      }
    }

    return amount;
  }

  private static List<List<Integer>> generateCombinations(List<Integer> inputList) {
    List<List<Integer>> result = new ArrayList<>();
    generateCombinationsHelper(inputList, new ArrayList<>(), 0, result);
    return result;
  }

  private static void generateCombinationsHelper(
      List<Integer> inputList,
      List<Integer> currentCombination,
      int currentIndex,
      List<List<Integer>> result) {

    // Add the current combination to the result
    result.add(new ArrayList<>(currentCombination));

    // Generate combinations by considering each element in the remaining part of the list
    for (int i = currentIndex; i < inputList.size(); i++) {
      // Choose
      currentCombination.add(inputList.get(i));

      // Explore
      generateCombinationsHelper(inputList, currentCombination, i + 1, result);

      // Unchoose (backtrack)
      currentCombination.removeLast();
    }
  }
}
