package rule;

public class MoreThanNLengthRule implements IndexingRule {

  private int filterLength = 0;
  public MoreThanNLengthRule(int filterLength) {
    this.filterLength = filterLength;
  }

  @Override
  public Object result(String word) {
    return word.length() > filterLength;
  }
}
