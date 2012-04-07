package test.kb;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import main.kb.Constant;
import main.kb.Replacement;
import main.kb.Statement;
import main.kb.Variable;

import org.junit.Test;

public class StatementTest {
   
   @Test
   public void replace_variable_with_constant_value() throws Exception{
      Statement original = new Statement("ageOf", new Variable("X"), new Variable("Y"));
      Statement newStatement = original.replaceVariableWithValue(new Variable("X"), new Constant<String>("bob"));
      assertEquals(new Statement("ageOf", new Constant<String>("bob"), new Variable("Y")), newStatement);
      
      Statement newStatement2 = original.replaceVariableWithValue(new Variable("Y"), new Constant<Number>(20));
      assertEquals(new Statement("ageOf", new Variable("X"), new Constant<Number>(20)), newStatement2);
      
      Statement newStatement3 = newStatement2.replaceVariableWithValue(new Variable("X"), new Constant<String>("bob"));
      assertEquals(new Statement("ageOf", new Constant<String>("bob"), new Constant<Number>(20)), newStatement3);
   }
   
   @Test
   public void replace_multiple_variables_of_same_name() throws Exception{
      Statement original = new Statement("loveTriangle", new Variable("X"), new Variable("X"), new Variable("Y"));
      Statement newStatement = original.replaceVariableWithValue(new Variable("X"), new Constant<String>("bob"));
      assertEquals(new Statement("loveTriangle", new Constant<String>("bob"), new Constant<String>("bob"), new Variable("Y")), newStatement);
   }
   
   @Test
   public void get_matched_variable_replacements() throws Exception{
      Statement constant = new Statement("ageOf", new Constant<String>("bob"), new Constant<Number>(17));
      Statement variable = new Statement("ageOf", new Variable("X"), new Variable("Y"));
      List<Replacement> replacements = variable.unifyWith(constant);
      assertEquals(Arrays.asList(new Replacement(new Variable("X"), new Constant<String>("bob")), new Replacement(new Variable("Y"), new Constant<Number>(17))), replacements);
   }
   
   @Test
   public void if_unification_impossible_no_replacements() throws Exception{
      Statement constant = new Statement("ageOf", new Constant<String>("bob"), new Constant<Number>(17));
      Statement variable = new Statement("ageOf", new Variable("X"), new Variable("X"));
      assertTrue(variable.unifyWith(constant).isEmpty());
   }
   
   @Test
   public void three_different_variable_replacements() throws Exception{
      Statement constant = new Statement("p", new Constant<String>("a"), new Constant<String>("b"), new Constant<String>("c"));
      Statement variable = new Statement("p", new Variable("X"), new Variable("Y"), new Variable("Z"));
      List<Replacement> replacements = variable.unifyWith(constant);
      assertEquals(Arrays.asList(
            new Replacement(new Variable("X"), new Constant<String>("a")), 
            new Replacement(new Variable("Y"), new Constant<String>("b")),
            new Replacement(new Variable("Z"), new Constant<String>("c"))), replacements);
   }
   
   @Test
   public void two_replacements_but_one_of_them_gets_a_bunch_of_variables() throws Exception{
      Statement constant = new Statement("p", new Constant<String>("a"), new Constant<String>("b"), new Constant<String>("b"));
      Statement variable = new Statement("p", new Variable("X"), new Variable("Y"), new Variable("Y"));
      List<Replacement> replacements = variable.unifyWith(constant);
      assertEquals(Arrays.asList(
            new Replacement(new Variable("X"), new Constant<String>("a")), 
            new Replacement(new Variable("Y"), new Constant<String>("b"))), replacements);
   }

}
