package rule;

public class CountWordLengthRule implements IndexingRule {
  @Override
  public Object result(String word) {

    return word.length();
  }
}
