package test.kb;

import static junit.framework.Assert.assertEquals;
import static main.kb.KBEncoding.*;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import main.kb.Statement;

import org.junit.Test;

public class StatementTest {
   
   @Test
   public void replace_variable_with_constant_value() throws Exception{
      Statement original = statement("ageOf(X, Y)");
      Statement newStatement = original.replaceVariableWithValue(variable("X"), constant("bob"));
      assertEquals(statement("ageOf(bob, Y)"), newStatement);
      
      Statement newStatement2 = original.replaceVariableWithValue(variable("Y"), constant(20));
      assertEquals(statement("ageOf(X, 20)"), newStatement2);
      
      Statement newStatement3 = newStatement2.replaceVariableWithValue(variable("X"), constant("bob"));
      assertEquals(statement("ageOf(bob, 20)"), newStatement3);
   }
   
   @Test
   public void unify_with_statement_with_different_variable_names() throws Exception{
      Statement s1 = statement("p(A, B)");
      Statement s2 = statement("p(X, Y)");
      assertTrue(s1.match(s2));
      assertEquals(Arrays.asList(replacement("A", "X"), replacement("B", "Y")), s1.unifyWith(s2));
      assertEquals(Arrays.asList(replacement("X", "A"), replacement("Y", "B")), s2.unifyWith(s1));
   }
   
   @Test
   public void replace_multiple_variables_of_same_name() throws Exception{
      Statement original = statement("loveTriangle(X, X, Y)");
      Statement newStatement = original.replaceVariableWithValue(variable("X"), constant("bob"));
      assertEquals(statement("loveTriangle(bob, bob, Y)"), newStatement);
   }
   
   @Test
   public void get_matched_variable_replacements() throws Exception{
      Statement constant = statement("ageOf(bob, 17)");
      Statement variable = statement("ageOf(X, Y)");
      assertEquals(Arrays.asList(replacement("X", "bob"), replacement("Y", 17)), variable.unifyWith(constant));
   }
   
   @Test
   public void if_unification_impossible_no_replacements() throws Exception{
      Statement constant = statement("ageOf(bob, 17)");
      Statement variable = statement("ageOf(X, X)");
      assertTrue(variable.unifyWith(constant).isEmpty());
   }
   
   @Test
   public void three_different_variable_replacements() throws Exception{
      Statement constant = statement("p(a, b, c)");
      Statement variable = statement("p(X, Y, Z)");
      assertEquals(Arrays.asList(
            replacement("X", "a"), 
            replacement("Y", "b"),
            replacement("Z", "c")), variable.unifyWith(constant));
   }
   
   @Test
   public void two_replacements_but_one_of_them_gets_a_bunch_of_variables() throws Exception{
      Statement constant = statement("p(a, b, b)");
      Statement variable = statement("p(X, Y, Y)");
      assertEquals(Arrays.asList(
            replacement("X", "a"), 
            replacement("Y", "b")), variable.unifyWith(constant));
   }

}
