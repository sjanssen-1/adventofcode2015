public class Puzzle {

  public static String calculateNextValidPassword(String password) {
    do {
      password = calculateNextPassword(password);
    } while (!isValidPassword(password));
    return password;
  }

  public static String calculateNextPassword(String password) {
    if (password.chars().allMatch(value -> value == 'z')) {
      return "a".repeat(password.length() + 1);
    }

    final var reversedPassword =
        password.chars().mapToObj(value -> (char) value).toList().reversed();
    //            .toArray(new Character[0]);
    final var nextPassword = new StringBuilder();
    var incremented = false;
    for (Character character : reversedPassword) {
      if (incremented) {
        nextPassword.append(character);
      } else if (character == 'z') {
        nextPassword.append('a');
      } else {
        nextPassword.append((char) (character + 1));
        incremented = true;
      }
    }

    return nextPassword.reverse().toString();
  }

  public static boolean isValidPassword(String password) {
    return satisfiesFirstRequirement(password)
        && satisfiesSecondRequirement(password)
        && satisfiesThirdRequirement(password);
  }

  private static boolean satisfiesFirstRequirement(String password) {
    var passwordCharacters = password.toCharArray();
    var counter = 0;

    for (int i = 0; i < password.length() - 1; i++) {
      if (passwordCharacters[i + 1] - passwordCharacters[i] == 1) {
        counter++;
      } else {
        counter = 0;
      }

      if (counter == 2) {
        return true;
      }
    }
    return false;
  }

  private static boolean satisfiesSecondRequirement(String password) {
    return !password.contains("i") && !password.contains("o") && !password.contains("l");
  }

  private static boolean satisfiesThirdRequirement(String password) {
    var passwordCharacters = password.toCharArray();
    var counter = 0;

    for (int i = 0; i < password.length() - 1; i++) {
      if (passwordCharacters[i] == passwordCharacters[i + 1]) {
        counter++;
        i++;
      }
    }

    return counter >= 2;
  }
}
