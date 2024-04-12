package rule;

public class UppercaseWordRule implements IndexingRule {
  @Override
  public Object result(String word) {
    return Character.isUpperCase(word.charAt(0));
  }
}
