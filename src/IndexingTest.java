import model.WordIndex;
import org.junit.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IndexingTest {

  @Test
  public void processFiles(){
    // Given
    Indexing indexing = new Indexing();
    String[] fileNames = {"src/file/test.txt"};

    // When
    WordIndex result = indexing.processFiles(fileNames);

    // Then
    assertNotNull(result);
    assertTrue(result.isWordExist("longestwordinthistext"));
    assertEquals(5, result.countIsMoreThan5Length());
    assertEquals(3, result.countIsUppercase());
    assertEquals(1, result.countIsMoreThanNLength(10));
  }

}