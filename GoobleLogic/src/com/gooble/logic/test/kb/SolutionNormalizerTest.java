package com.gooble.logic.test.kb;

import static com.gooble.logic.kb.KBEncoding.replacement;
import static com.gooble.logic.kb.KBEncoding.solution;
import static junit.framework.Assert.assertEquals;

import java.util.Arrays;


import org.junit.Test;

import com.gooble.logic.kb.SolutionNormalizer;
import com.gooble.logic.kb.SolutionSet;

public class SolutionNormalizerTest {

   @Test
   public void normalize_simple_solution() throws Exception{
      SolutionSet solns = new SolutionSet(Arrays.asList(solution(replacement("X", 1))), true);
      SolutionSet newSolns = new SolutionNormalizer(Arrays.asList(replacement("H", "X"))).normalize(solns);
      SolutionSet expectedSolns = new SolutionSet(Arrays.asList(solution(replacement("H", 1))), true);
      assertEquals(expectedSolns, newSolns);
   }
   
   @Test
   public void remove_extraneous_variables_accumulated_from_sub_solutions() throws Exception{
      SolutionSet solns = new SolutionSet(Arrays.asList(solution(replacement("X", 1), replacement("H", "bob"))), true);
      SolutionSet newSolns = new SolutionNormalizer(Arrays.asList(replacement("H", "X"))).normalize(solns);
      SolutionSet expectedSolns = new SolutionSet(Arrays.asList(solution(replacement("H", 1))), true);
      assertEquals(expectedSolns, newSolns);
   }
   
}
