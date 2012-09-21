package com.gooble.logic.test.app.entity;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import com.gooble.logic.app.entity.Variable;
import com.gooble.logic.app.entity.domain.VariableType;

public class VariableUTest {

   @Test
   public void if_variable_is_text_then_show_only_equals_operator() throws Exception{
      Variable variable = new Variable();
      variable.setType(VariableType.TEXT);
      assertEquals(Arrays.asList("="), variable.getOperators());
   }
   @Test
   public void if_variable_is_number_then_show_greater_less_and_equal_operators() throws Exception{
      Variable variable = new Variable();
      variable.setType(VariableType.NUMBER);
      assertEquals(Arrays.asList("<", ">", "="), variable.getOperators());
   }
}
