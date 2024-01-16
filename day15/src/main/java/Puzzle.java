import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import org.apache.commons.collections4.iterators.PermutationIterator;

public class Puzzle {
  public static int findBestScoringCookie(List<String> ingredients, boolean includeCalories) {
    final var teaspoonPermutations = generatePermutationsParts(100, ingredients.size());
    final var ingredientList = ingredients.stream().map(Ingredient::parse).toList();

    var bestCookieScore = 0;

    for (List<Integer> teaspoonPermutation : teaspoonPermutations) {

      final var permutationIterator = new PermutationIterator<>(teaspoonPermutation);
      while (permutationIterator.hasNext()) {
        final var permutation = permutationIterator.next();
        int capacity = 0;
        int durability = 0;
        int flavor = 0;
        int texture = 0;
        int calories = 0;
        for (int i = 0; i < ingredientList.size(); i++) {
          capacity += ingredientList.get(i).calculateCapacityScore(permutation.get(i));
          durability += ingredientList.get(i).calculateDurabilityScore(permutation.get(i));
          flavor += ingredientList.get(i).calculateFlavorScore(permutation.get(i));
          texture += ingredientList.get(i).calculateTextureScore(permutation.get(i));
          calories += ingredientList.get(i).calculateCalorieScore(permutation.get(i));
        }

        if (includeCalories && calories != 500) {
          continue;
        }
        bestCookieScore =
            Math.max(
                bestCookieScore,
                Math.max(capacity, 0)
                    * Math.max(durability, 0)
                    * Math.max(flavor, 0)
                    * Math.max(texture, 0));
      }
    }

    return bestCookieScore;
  }

  public static List<List<Integer>> generatePermutationsParts(int total, int parts) {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> currentPermutation = new ArrayList<>();

    generatePermutationsHelper(total, parts, result, currentPermutation, 1);

    return result;
  }

  private static void generatePermutationsHelper(
      int remainingTotal,
      int remainingParts,
      List<List<Integer>> result,
      List<Integer> currentPermutation,
      int start) {
    // Base case: all parts filled and the sum equals the total
    if (remainingParts == 0 && remainingTotal == 0) {
      result.add(new ArrayList<>(currentPermutation));
      return;
    }

    // Recursive case: try different values for the current part
    for (int i = start; i <= remainingTotal; i++) {
      currentPermutation.add(i);
      generatePermutationsHelper(
          remainingTotal - i, remainingParts - 1, result, currentPermutation, i);
      currentPermutation.removeLast();
    }
  }

  @Builder
  record Ingredient(int capacity, int durability, int flavor, int texture, int calories) {
    static Ingredient parse(String string) {
      final var split = string.replace(",", "").split(" ");
      return Ingredient.builder()
          .capacity(Integer.parseInt(split[2]))
          .durability(Integer.parseInt(split[4]))
          .flavor(Integer.parseInt(split[6]))
          .texture(Integer.parseInt(split[8]))
          .calories(Integer.parseInt(split[10]))
          .build();
    }

    int calculateCapacityScore(int teaspoons) {
      return teaspoons * capacity;
    }

    int calculateDurabilityScore(int teaspoons) {
      return teaspoons * durability;
    }

    int calculateFlavorScore(int teaspoons) {
      return teaspoons * flavor;
    }

    int calculateTextureScore(int teaspoons) {
      return teaspoons * texture;
    }

    int calculateCalorieScore(int teaspoons) {
      return teaspoons * calories;
    }
  }
}
