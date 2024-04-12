package model;

import java.util.HashMap;
import java.util.Map;

public class WordMetadata {
  private String word;
  private final Map<String, Object> ruleResults;

  public WordMetadata(String word) {
    this.word = word;
    this.ruleResults = new HashMap<>();
  }

  public String getWord() {
    return word;
  }

  public Map<String, Object> getRuleResults() {
    return ruleResults;
  }

  public void setRuleResult(String ruleName, Object result) {
    ruleResults.put(ruleName, result);
  }

}

