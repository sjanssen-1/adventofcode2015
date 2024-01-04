import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class PuzzleHelper {
  public static String readInputString(String fileName) throws IOException {
    ClassLoader classLoader = PuzzleHelper.class.getClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream(fileName);

    if (inputStream == null) {
      throw new IOException("File not found: " + fileName);
    }

    try (Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8)) {
      return scanner.useDelimiter("\\A").next();
    }
  }

  public static List<String> readInputList(String fileName) throws IOException {
    ClassLoader classLoader = PuzzleHelper.class.getClassLoader();
    Path filePath = Paths.get(Objects.requireNonNull(classLoader.getResource(fileName)).getPath());

    return Files.readAllLines(filePath, StandardCharsets.UTF_8);
  }
}
