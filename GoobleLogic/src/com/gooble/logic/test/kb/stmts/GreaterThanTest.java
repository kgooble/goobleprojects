package com.gooble.logic.test.kb.stmts;

import static com.gooble.logic.kb.encoding.KBEncoding.statement;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.*;

import org.junit.Test;

public class GreaterThanTest {

   @Test
   public void greaterThan_toString() throws Exception{
      assertEquals("(CONST:17 > CONST:18)", statement("17 > 18").toString());
      assertEquals("(VAR:X > CONST:18)", statement("X > 18").toString());
   }
   
   @Test
   public void evaluate() throws Exception{
      assertTrue(statement("18 > 12").evaluate());
      assertFalse(statement("18 > 19").evaluate());
      assertFalse(statement("18 > X").evaluate());
   }
}
