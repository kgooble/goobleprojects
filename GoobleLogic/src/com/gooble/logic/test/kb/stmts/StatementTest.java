package com.gooble.logic.test.kb.stmts;

import static com.gooble.logic.kb.KBEncoding.*;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;


import org.junit.Test;

import com.gooble.logic.kb.stmts.Statement;

public class StatementTest {
   
   @Test
   public void replace_G_with_Y_and_Y_with_X_works_on_original_statement_doesnt_override_already_replaced_things() throws Exception{
      Statement workingStatement = statement("ageOf(G, Y)").applyReplacements(Arrays.asList(replacement("G", "Y"), replacement("Y", "X")));
      assertEquals(statement("ageOf(Y, X)"), workingStatement);
   }
   
   @Test
   public void replace_variable_with_constant_value() throws Exception{
      Statement original = statement("ageOf(X, Y)");
      Statement newStatement = original.applyReplacements(Arrays.asList(replacement("X", "bob")));
      assertEquals(statement("ageOf(bob, Y)"), newStatement);
      
      Statement newStatement2 = original.applyReplacements(Arrays.asList(replacement("Y", 20)));
      assertEquals(statement("ageOf(X, 20)"), newStatement2);
      
      Statement newStatement3 = newStatement2.applyReplacements(Arrays.asList(replacement("X", "bob")));
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
      Statement newStatement = original.applyReplacements(Arrays.asList(replacement("X", "bob")));
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
   
   @Test(expected=UnsupportedOperationException.class)
   public void cannot_call_evaluate_on_statement() throws Exception{
      statement("p(X)").evaluate();
   }
   
   @Test
   public void equals_other_statement_with_same_number_of_terms_and_same_name() throws Exception{
      assertFalse(statement("p(X)").equals(statement("p(X, Y)")));
      assertTrue(statement("p(X)").equals(statement("p(X)")));
   }

}
