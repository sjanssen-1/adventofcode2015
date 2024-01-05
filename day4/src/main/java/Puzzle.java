import org.apache.commons.codec.digest.DigestUtils;

public class Puzzle {
  public static int findHash(String secretKey, String startingZeroes) {
    int number = 1;
    String toCheck = secretKey + number;
    while (!DigestUtils.md5Hex(toCheck).startsWith(startingZeroes)) {
      number++;
      toCheck = secretKey + number;
    }
    return number;
  }
}
