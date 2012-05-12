package com.gooble.logic.test.puzzle;

import static com.gooble.logic.kb.encoding.KBEncoding.statement;
import static junit.framework.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import com.gooble.logic.puzzle.SolutionDefinition;

public class SolutionDefinitionTest {
   @Test
   public void adding_solution_creates_list_of_statements_encoding_those_solutions_based_on_main_and_variable_names() throws Exception {
      SolutionDefinition solDef = new SolutionDefinition();
      solDef.setExpectedOrder("woman", "house", "hair");
      solDef.add("adele", 2, "brown");
      
      assertEquals(Arrays.asList(
            statement("houseOf(adele, 2)"), 
            statement("hairOf(adele, brown)")), 
            solDef.getStatements());
   }
}
