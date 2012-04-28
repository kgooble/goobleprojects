package com.gooble.logic.test.kb;

import static com.gooble.logic.kb.encoding.OperatorEncoding.operator;
import static com.gooble.logic.kb.encoding.TermEncoding.constant;
import static com.gooble.logic.kb.encoding.TermEncoding.variable;
import static junit.framework.Assert.assertEquals;

import org.junit.Test;

import com.gooble.logic.kb.Equals;
import com.gooble.logic.kb.stmts.GreaterThan;
import com.gooble.logic.kb.stmts.LessThan;

public class OperatorFactoryTest {
   @Test
   public void create_greater_than_operator() throws Exception {
      assertEquals(new GreaterThan(constant(17), variable("X")), operator("X > 17"));
   }
   @Test
   public void create_less_than_operator() throws Exception {
      assertEquals(new LessThan(constant(17), variable("X")), operator("X < 17"));
   }
   @Test
   public void create_equals_operator() throws Exception {
      assertEquals(new Equals(constant(17), variable("X")), operator("X = 17"));
   }
}
