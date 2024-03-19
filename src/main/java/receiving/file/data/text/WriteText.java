package receiving.file.data.text;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteText {
  public static void writeText(String txt, String nameProject) {
    String fileName = "src/main/resources/%s.txt".formatted(nameProject);

    try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
      bufferedWriter.write(txt);
    } catch (IOException e) {
      throw new RuntimeException("Error reading file: " + e.getMessage(), e);
    }
  }
}