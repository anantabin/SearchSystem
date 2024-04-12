package model;

import rule.CountWordLengthRule;
import rule.MoreThanNLengthRule;
import rule.UppercaseWordRule;

import java.util.HashSet;
import java.util.Set;

public class WordIndex {

  private Set<WordMetadata> index;

  public WordIndex() {
    this.index = new HashSet<>();
  }

  public void addWordMetadata(WordMetadata wordMetadata) {
    if (!isWordExist(wordMetadata.getWord())) {
      index.add(wordMetadata);
    }
  }

  public long countIsUppercase() {
    return index.stream()
            .filter(metadata -> (boolean) metadata.getRuleResults().getOrDefault(UppercaseWordRule.class.getSimpleName(), false))
            .count();
  }

  public long countIsMoreThan5Length() {
    return index.stream()
            .filter(metadata -> (boolean) metadata.getRuleResults().getOrDefault(MoreThanNLengthRule.class.getSimpleName(), false))
            .count();
  }

  public long countIsMoreThanNLength(int wordLength) {
    return index.stream()
            .filter(metadata -> (int) metadata.getRuleResults().getOrDefault(CountWordLengthRule.class.getSimpleName(), 0) > wordLength)
            .count();
  }

  public void displayWordMoreThan5Length(int limit) {
    index.stream()
            .filter(metadata -> (boolean) metadata.getRuleResults().getOrDefault(MoreThanNLengthRule.class.getSimpleName(), false))
            .map(WordMetadata::getWord)
            .limit(limit)
            .forEach(System.out::println);
  }

  public void displayWordMoreThanNLength(int wordLength, int limit) {
    index.stream()
            .filter(metadata -> (int) metadata.getRuleResults().getOrDefault(CountWordLengthRule.class.getSimpleName(), 0) > wordLength)
            .map(WordMetadata::getWord)
            .limit(limit)
            .forEach(System.out::println);
  }

  public Boolean isWordExist(String word) {
    return index.stream()
            .anyMatch(metadata -> metadata.getWord().equals(word));
  }
}
