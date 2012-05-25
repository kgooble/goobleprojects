package com.gooble.logic.test.puzzle;

import static com.gooble.logic.kb.encoding.KBEncoding.statement;
import static com.gooble.logic.test.TestUtilities.set;
import static junit.framework.Assert.assertEquals;

import org.junit.Test;

import com.gooble.logic.puzzle.StatementFrom;

public class StatementFromTest {
   @Test
   public void create_statement_with_numbered_variables() throws Exception {
      assertEquals(statement("solution1(V1, V2, V3, V4, V5)"), 
            new StatementFrom("solution1").numberOfVariables(5));
   }
   @Test
   public void create_statement_with_variables() throws Exception {
      assertEquals(statement("solution1(BOBAGE, BOBHEIGHT)"), 
            new StatementFrom("solution1").variables("bob", set("age", "height")));
   }
}
