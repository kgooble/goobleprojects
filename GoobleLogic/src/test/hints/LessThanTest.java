package test.hints;

import static junit.framework.Assert.assertEquals;
import static main.hints.Hint.LessThan;

import java.util.Arrays;

import main.Variable;

import org.junit.Test;

@SuppressWarnings("unchecked")
public class LessThanTest {
   
   @Test
   public void number_can_have_less_than_hints() throws Exception{
      Variable<Number> var = new Variable<Number>(23, 2.0, 6);
      assertEquals(Arrays.asList(2.0, 6), var.givenHints(LessThan(10)).listPossibleValues());
      assertEquals(Arrays.asList(23, 2.0, 6), var.givenHints(LessThan(50)).listPossibleValues());
   }
   
}
