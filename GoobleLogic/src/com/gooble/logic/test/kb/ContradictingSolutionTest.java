package com.gooble.logic.test.kb;

import static com.gooble.logic.kb.KBEncoding.replacement;
import static com.gooble.logic.kb.KBEncoding.rule;
import static com.gooble.logic.kb.KBEncoding.solution;
import static com.gooble.logic.kb.KBEncoding.statement;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.gooble.logic.kb.ContradictingSolution;
import com.gooble.logic.kb.Rule;
import com.gooble.logic.kb.solutions.Solution;
import com.gooble.logic.kb.stmts.Statement;

public class ContradictingSolutionTest {

   @Test
   public void solution_that_does_not_contradict() throws Exception{
      assertFalse(isContradictory(
            solution(replacement("X", "bob")),
            rule("p(X) => g(X)")));
   }
   
   @Test
   public void contradictory_solution() throws Exception{
      assertTrue(isContradictory(
            solution(replacement("X", "bob"), replacement("B", "darm"), replacement("Y", "carey")),
            rule("p(X, B) ^ p(X, Z) => g(X, B, Y, Z)")));
   }
   
   @Test
   public void if_you_ignore_it_no_contradictions() throws Exception{
      assertFalse(isContradictory(
            solution(replacement("X", "bob"), replacement("B", "darm"), replacement("Y", "carey")),
            rule("p(X, B) ^ p(X, Z) => g(X, B, Y, Z)"),
            statement("p(X, Y)")
            ));
   }

   private boolean isContradictory(Solution soln, Rule rule, Statement... ignoreList) {
      return new ContradictingSolution(soln).isContradictoryBasedOnRule(rule, Arrays.asList(ignoreList));
   }
   
}
