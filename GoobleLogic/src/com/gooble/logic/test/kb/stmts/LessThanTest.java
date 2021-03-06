package com.gooble.logic.test.kb.stmts;

import static com.gooble.logic.kb.encoding.KBEncoding.statement;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class LessThanTest {
   
   @Test
   public void lessThan_toString() throws Exception{
      assertEquals("(CONST:17 < CONST:18)", statement("17 < 18").toString());
      assertEquals("(VAR:X < CONST:18)", statement("X < 18").toString());
   }
   
   @Test
   public void evaluate() throws Exception{
      assertTrue(statement("17 < 18").evaluate());
      assertFalse(statement("18 < 18").evaluate());
      assertFalse(statement("19 < 18").evaluate());
      assertFalse(statement("X < 18").evaluate());
      assertFalse(statement("17 < X").evaluate());
      assertFalse(statement("X < Y").evaluate());
   }

}
