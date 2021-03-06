package com.gooble.logic.test.kb.solutions;

import static com.gooble.logic.kb.encoding.KBEncoding.replacement;
import static com.gooble.logic.kb.encoding.KBEncoding.solution;
import static junit.framework.Assert.assertEquals;

import java.util.Arrays;


import org.junit.Test;

import com.gooble.logic.kb.solutions.Normalize;
import com.gooble.logic.kb.solutions.SolutionSet;

public class NormalizeTest {

   @Test
   public void normalize_simple_solution() throws Exception{
      SolutionSet solns = new SolutionSet(Arrays.asList(solution(replacement("X", 1))), true);
      SolutionSet newSolns = new Normalize(Arrays.asList(replacement("H", "X"))).solutions(solns);
      SolutionSet expectedSolns = new SolutionSet(Arrays.asList(solution(replacement("H", 1))), true);
      assertEquals(expectedSolns, newSolns);
   }
   
   @Test
   public void remove_extraneous_variables_accumulated_from_sub_solutions() throws Exception{
      SolutionSet solns = new SolutionSet(Arrays.asList(solution(replacement("X", 1), replacement("H", "bob"))), true);
      SolutionSet newSolns = new Normalize(Arrays.asList(replacement("H", "X"))).solutions(solns);
      SolutionSet expectedSolns = new SolutionSet(Arrays.asList(solution(replacement("H", 1))), true);
      assertEquals(expectedSolns, newSolns);
   }
   
}
