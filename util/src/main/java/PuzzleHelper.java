import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.io.IOUtils;

public class PuzzleHelper {
  public static String readInputString(String fileName) throws IOException {
    try (Scanner scanner = new Scanner(getInputStream(fileName), StandardCharsets.UTF_8)) {
      return scanner.useDelimiter("\\A").next();
    }
  }

  public static List<String> readInputList(String fileName) throws IOException {
    return IOUtils.readLines(getInputStream(fileName), StandardCharsets.UTF_8);
  }

  private static InputStream getInputStream(String fileName) throws IOException {
    var classLoader = PuzzleHelper.class.getClassLoader();
    var inputStream = classLoader.getResourceAsStream(fileName);
    if (inputStream == null) {
      throw new IOException("File not found: " + fileName);
    }
    return classLoader.getResourceAsStream(fileName);
  }
}
