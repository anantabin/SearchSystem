import model.WordIndex;
import validator.Validator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileProcessor implements Runnable {
  private final Validator validator;
  private final String fileName;
  private final WordIndex wordIndex;

  public FileProcessor(String fileName, WordIndex wordIndex, Validator validator) {
    this.fileName = fileName;
    this.wordIndex = wordIndex;
    this.validator = validator;
  }

  @Override
  public void run() {
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = reader.readLine())!=null) {
        if (!line.trim().isEmpty()) {
          String[] lineWords = line.split("\\s+");
          for (String word : lineWords) {
            if (!word.isEmpty()) {
              wordIndex.addWordMetadata(validator.validate(word));
            }
          }
        }
      }
    } catch (IOException e) {
      System.err.println("Error reading file: " + fileName + ". " + e.getMessage());
    }
  }
}
