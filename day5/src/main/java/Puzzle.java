import java.util.List;

public class Puzzle {

  private static final List<Character> vowels = List.of('a', 'e', 'i', 'o', 'u');
  private static final List<String> illegalStrings = List.of("ab", "cd", "pq", "xy");

  public static boolean isNiceString(String string) {
    return hasThreeVowels(string) && hasDoubleLetter(string) && !containsIllegalStrings(string);
  }

  public static boolean isNiceStringButLessRidiculous(String string) {
    return hasPairOfDoubleNonOverlappingLetters(string)
        && hasRepeatingLetterWithAnyOtherLetterInBetween(string);
  }

  private static boolean hasThreeVowels(String string) {
    int match = 0;
    for (char character : string.toCharArray()) {
      if (vowels.contains(character)) {
        match++;
      }
      if (match == 3) {
        return true;
      }
    }
    return false;
  }

  private static boolean hasDoubleLetter(String string) {
    for (int i = 1; i < string.length(); i++) {
      if (string.charAt(i - 1) == string.charAt(i)) {
        return true;
      }
    }
    return false;
  }

  private static boolean containsIllegalStrings(String string) {
    return illegalStrings.stream().anyMatch(string::contains);
  }

  private static boolean hasPairOfDoubleNonOverlappingLetters(String string) {
    for (int i = 1; i < string.length(); i++) {
      var previousChar = string.charAt(i - 1);
      var currentChar = string.charAt(i);

      if (string.indexOf("" + previousChar + currentChar, i + 1) != -1) {
        return true;
      }
    }
    return false;
  }

  private static boolean hasRepeatingLetterWithAnyOtherLetterInBetween(String string) {
    for (int i = 1; i < string.length() - 1; i++) {
      var previousChar = string.charAt(i - 1);
      var nextChar = string.charAt(i + 1);

      if (previousChar == nextChar) {
        return true;
      }
    }
    return false;
  }
}
