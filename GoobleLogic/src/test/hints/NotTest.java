package test.hints;

import static junit.framework.Assert.assertEquals;
import static main.hints.Hint.*;

import java.util.Arrays;

import main.Variable;

import org.junit.Test;

public class NotTest {
   
   @Test
   public void possible_variable_values_correct_given_domain_and_hints() throws Exception{
      Variable<String> var = new Variable<String>("cow", "monkey", "sheep");
      assertEquals(Arrays.asList("cow", "monkey"), var.givenHints(Not("sheep")).listPossibleValues());
      assertEquals(Arrays.asList("monkey", "sheep"), var.givenHints(Not("cow")).listPossibleValues());
      assertEquals(Arrays.asList("cow"), var.givenHints(Not("sheep"), Not("monkey")).listPossibleValues());
      assertEquals(Arrays.asList("cow", "monkey", "sheep"), var.givenHints(Not("bluejay")).listPossibleValues());
   }
   
   @Test
   public void not_takes_hints_for_example_less_than() throws Exception{
      Variable<Number> var = new Variable<Number>(4, 1, 10.0);
      assertEquals(Arrays.asList(10.0), var.givenHints(Not(LessThan(5))).listPossibleValues());
   }
   
}
