package test.hints;

import static junit.framework.Assert.assertEquals;
import static main.hints.Hint.GreaterThan;

import java.util.Arrays;

import main.Variable;

import org.junit.Test;

@SuppressWarnings("unchecked")
public class GreaterThanTest {
   
   @Test
   public void number_can_have_greater_than_hints() throws Exception{
      Variable<Number> var = new Variable<Number>(23, 2.0, 6);
      assertEquals(Arrays.asList(23), var.givenHints(GreaterThan(20)).listPossibleValues());
      assertEquals(Arrays.asList(23, 2.0, 6), var.givenHints(GreaterThan(1)).listPossibleValues());
   }
}
