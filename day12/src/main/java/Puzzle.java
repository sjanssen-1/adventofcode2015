import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class Puzzle {
  public static int findSum(String jsonString, boolean ignoreRed) throws IOException {
    final var objectMapper = new ObjectMapper();
    final var json = objectMapper.readTree(jsonString);
    return findSum(json, 0, ignoreRed);
  }

  private static int findSum(JsonNode node, int sum, boolean ignoreRed) {
    if (ignoreRed && node.isObject()) {
      for (JsonNode n : node) {
        if (n.isTextual() && n.asText().equals("red")) {
          return sum;
        }
      }
    }

    if (node.isObject() || node.isArray()) {
      int newSum = sum;
      for (JsonNode n : node) {
        if (n.isNumber()) {
          newSum += n.asInt();
        } else {
          newSum = findSum(n, newSum, ignoreRed);
        }
      }
      return newSum;
    } else if (node.isNumber()) {
      return sum + node.asInt();
    } else {
      return sum;
    }
  }
}
