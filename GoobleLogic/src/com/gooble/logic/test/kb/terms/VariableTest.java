package com.gooble.logic.test.kb.terms;

import static com.gooble.logic.kb.encoding.TermEncoding.constant;
import static com.gooble.logic.kb.encoding.TermEncoding.variable;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import com.gooble.logic.kb.terms.Variable;

public class VariableTest {

   private Variable x;

   @Before 
   public void create_variable() {
      x = variable("X");
   }
   
   @Test
   public void match_anything() throws Exception{
      assertTrue(x.match(variable("X")));
      assertTrue(x.match(variable("Y")));
      assertTrue(x.match(constant("x")));
   }
   
   @Test 
   public void equals_only_exact_same_variable() throws Exception{
      assertTrue(x.equals(variable("X")));
      assertFalse(x.equals(variable("Y")));
      assertFalse(x.equals(constant(10)));
   }
   
}
