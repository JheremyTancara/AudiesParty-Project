package receiving.file.data.text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.stream.Collectors;

public class ReadText {
  public static String readText(String fileName) {
    try (InputStream inputStream = ReadText.class.getClassLoader().getResourceAsStream(fileName);
         BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))) {
      return bufferedReader.lines().collect(Collectors.joining("\n"));
    } catch (IOException e) {
      throw new RuntimeException("Error reading file: " + fileName, e);
    }
  }
}