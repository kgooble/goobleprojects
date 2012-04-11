package com.gooble.logic.kb;

import static junit.framework.Assert.assertTrue;
import static main.kb.KBEncoding.constant;
import static main.kb.KBEncoding.variable;
import static org.junit.Assert.assertFalse;
import main.kb.Variable;

import org.junit.Before;
import org.junit.Test;

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
