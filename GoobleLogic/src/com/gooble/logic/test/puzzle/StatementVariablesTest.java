package com.gooble.logic.test.puzzle;

import static com.gooble.logic.kb.encoding.KBEncoding.statement;
import static com.gooble.logic.kb.encoding.TermEncoding.variable;
import static com.gooble.logic.test.TestUtilities.set;
import static junit.framework.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import com.gooble.logic.puzzle.StatementVariables;


public class StatementVariablesTest {

   @Test
   public void get_all_variables_from_statements() throws Exception {
      assertEquals(set(variable("X"), variable("Y")), 
            new StatementVariables(Arrays.asList(
            statement("olderThan(X, bob)"), 
            statement("shoesOf(X, red)"),
            statement("stupid(Y)"))).find());
   }
   
}
