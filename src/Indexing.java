import model.WordIndex;
import rule.CountWordLengthRule;
import rule.UppercaseWordRule;
import validator.Validator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Indexing {
  private final Validator validator = new Validator();
  private final WordIndex wordIndex = new WordIndex();

  private void addIndexingRules() {
    //Add more indexing rule if any
    validator.addIndexingRule(new UppercaseWordRule());
    validator.addIndexingRule(new CountWordLengthRule());
  }

  public WordIndex processFiles(String[] fileNames) {
    addIndexingRules();

    ExecutorService executorService = Executors.newFixedThreadPool(fileNames.length);

    for (String fileName : fileNames) {
      Runnable fileProcessorThread = new FileProcessor(fileName, wordIndex, validator);
      executorService.execute(fileProcessorThread);
    }

    executorService.shutdown();

    while (!executorService.isTerminated()) {
      // Do nothing
    }

    printResults();
    return wordIndex;
  }

  private void printResults() {
    System.out.println("Total number of words that start with upper case letter across all files: " + wordIndex.countIsUppercase());
    System.out.println("Total number of words that more than 5 letters across all files: " + wordIndex.countIsMoreThan5Length());
    System.out.println("Is word pellentesque exist in index: " + wordIndex.isWordExist("pellentesque"));
    System.out.println("Words that have more than 5 letters across all files: ");
    wordIndex.displayWordMoreThan5Length(10);
    System.out.println("Total number of words that more than 12 letters across all files: " + wordIndex.countIsMoreThanNLength(12));
    System.out.println("Words that have more than 12 letters across all files: ");
    wordIndex.displayWordMoreThanNLength(12, 100);
  }
}
