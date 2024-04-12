package validator;

import model.WordMetadata;
import rule.IndexingRule;
import rule.MoreThanNLengthRule;

import java.util.ArrayList;
import java.util.List;

public class Validator {
  private List<IndexingRule> indexingRules;

  public Validator() {
    this.indexingRules = new ArrayList<>();
    indexingRules.add(new MoreThanNLengthRule(5));
  }

  public void addIndexingRule(IndexingRule rule) {
    indexingRules.add(rule);
  }

  public WordMetadata validate(String word) {
    WordMetadata metadata = new WordMetadata(word);

    for (IndexingRule rule : indexingRules) {
      metadata.setRuleResult(rule.getClass().getSimpleName(), rule.result(word));
    }

    return metadata;
  }
}
