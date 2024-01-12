public class Puzzle {
  public static String lookAndSay(String string) {
    StringBuilder newSequence = new StringBuilder();

    char currentCharacter = string.charAt(0);
    int count = 1;

    for (int i = 1; i < string.length(); i++) {
      if (string.charAt(i) == currentCharacter) {
        count++;
      } else {
        newSequence.append(count).append(currentCharacter);
        count = 1;
        currentCharacter = string.charAt(i);
      }
    }

    newSequence.append(count).append(currentCharacter);
    return newSequence.toString();
  }
}
