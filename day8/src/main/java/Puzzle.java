import java.util.List;
import org.apache.commons.text.StringEscapeUtils;

public class Puzzle {

  public static int findStorageDifference(List<String> storage) {
    return storage.stream().map(Puzzle::findStorageDifference).reduce(0, Integer::sum);
  }

  public static int findStorageDifferenceForEscapedStrings(List<String> storage) {
    return storage.stream()
        .map(Puzzle::findStorageDifferenceForEscapedString)
        .reduce(0, Integer::sum);
  }

  private static int findStorageDifference(String string) {
    final int codeSize = string.length();
    final String unescapedString = string.replaceAll("\\\\x[0-9a-f]{2}", "'").translateEscapes();
    final int memorySize = unescapedString.substring(1, unescapedString.length() - 1).length();
    return codeSize - memorySize;
  }

  private static int findStorageDifferenceForEscapedString(String string) {
    final int originalCodeSize = string.length();
    final int escapedCodeSize = StringEscapeUtils.escapeJava(string).length() + 2;
    return escapedCodeSize - originalCodeSize;
  }
}
