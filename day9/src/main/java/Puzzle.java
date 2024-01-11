import java.util.*;
import org.apache.commons.collections4.iterators.PermutationIterator;

public class Puzzle {
  public static int findShortestRoute(List<String> distances) {
    final var cities = parseCities(distances);

    final var permutations = new PermutationIterator<>(cities.keySet());
    int shortestRoute = Integer.MAX_VALUE;
    while (permutations.hasNext()) {
      var permutation = permutations.next();
      int route = 0;
      for (int i = 1; i < permutation.size(); i++) {
        final var currentCity = permutation.get(i - 1);
        final var nextCity = permutation.get(i);
        final var distanceToNextCity = cities.get(currentCity).get(nextCity);
        route += distanceToNextCity;
      }
      shortestRoute = Math.min(shortestRoute, route);
    }

    return shortestRoute;
  }

  public static int findLongestRoute(List<String> distances) {
    final var cities = parseCities(distances);

    final var permutations = new PermutationIterator<>(cities.keySet());
    int longestRoute = Integer.MIN_VALUE;
    while (permutations.hasNext()) {
      var permutation = permutations.next();
      int route = 0;
      for (int i = 1; i < permutation.size(); i++) {
        final var currentCity = permutation.get(i - 1);
        final var nextCity = permutation.get(i);
        final var distanceToNextCity = cities.get(currentCity).get(nextCity);
        route += distanceToNextCity;
      }
      longestRoute = Math.max(longestRoute, route);
    }

    return longestRoute;
  }

  private static Map<String, Map<String, Integer>> parseCities(List<String> distances) {
    final var cities = new HashMap<String, Map<String, Integer>>();

    for (String distanceLine : distances) {
      final var split = distanceLine.split(" = ");
      final int distance = Integer.parseInt(split[1]);
      final var split2 = split[0].split(" to ");
      final var from = split2[0];
      final var to = split2[1];

      if (!cities.containsKey(from)) {
        final var neighbours = new HashMap<String, Integer>();
        neighbours.put(to, distance);
        cities.put(from, neighbours);
      } else {
        cities.get(from).put(to, distance);
      }

      if (!cities.containsKey(to)) {
        final var neighbours = new HashMap<String, Integer>();
        neighbours.put(from, distance);
        cities.put(to, neighbours);
      } else {
        cities.get(to).put(from, distance);
      }
    }
    return cities;
  }
}
