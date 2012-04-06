package test.hints;

import static junit.framework.Assert.assertEquals;
import static main.hints.Hint.Equals;

import java.util.Arrays;
import java.util.Collections;

import main.Variable;

import org.junit.Test;

@SuppressWarnings("unchecked")
public class EqualsTest {

   @Test
   public void filter_by_equality_strings() throws Exception{
      Variable<String> var = new Variable<String>("cow", "monkey", "sheep");
      assertEquals(Arrays.asList("sheep"), var.givenHints(Equals("sheep")).listPossibleValues());
      assertEquals(Arrays.asList("cow"), var.givenHints(Equals("cow")).listPossibleValues());
   }
   
   @Test
   public void filter_by_equality_numbers() throws Exception{
      Variable<Number> var = new Variable<Number>(5, 1, 10.0);
      assertEquals(Arrays.asList(10.0), var.givenHints(Equals((Number)10.0)).listPossibleValues());
      assertEquals(Arrays.asList(5), var.givenHints(Equals((Number)5)).listPossibleValues());
      assertEquals(Collections.emptyList(), var.givenHints(Equals((Number)1.5)).listPossibleValues());
   }
}
