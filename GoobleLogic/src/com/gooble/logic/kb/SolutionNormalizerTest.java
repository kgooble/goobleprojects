package com.gooble.logic.kb;

import static junit.framework.Assert.assertEquals;
import static main.kb.KBEncoding.replacement;
import static main.kb.KBEncoding.solution;

import java.util.Arrays;

import main.kb.SolutionNormalizer;
import main.kb.SolutionSet;

import org.junit.Test;

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
