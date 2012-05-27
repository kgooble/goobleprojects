package com.gooble.logic.test.kb;

import static com.gooble.logic.kb.encoding.KBEncoding.replacement;
import static com.gooble.logic.kb.encoding.KBEncoding.rule;
import static com.gooble.logic.kb.encoding.KBEncoding.solution;
import static com.gooble.logic.kb.encoding.KBEncoding.solutions;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

import java.util.Collections;

import org.junit.Test;

import com.gooble.logic.kb.ConsistentMerge;
import com.gooble.logic.kb.Rule;
import com.gooble.logic.kb.solutions.SolutionSet;
import com.gooble.logic.kb.stmts.Statement;

public class ConsistentMergeTest {
   
   private SolutionSet mergedSolutions;
   private Rule mergedRule;

   @Test
   public void merge_contradictory_solutions_result_in_empty_set() throws Exception{
      merge(rule("p(X, Y) => g(X, Y)"), 
            solutions(true, (solution(replacement("X", "bob"), replacement("Y", "joe")))),
            
            rule("p(X, Y) => h(X, Y)"), 
            solutions(true, solution(replacement("X", "bob"), replacement("Y", "mark"))));
      
      assertFalse(mergedSolutions.hasSolutions());
      assertFalse(mergedSolutions.isQueryTrue());
      assertEquals(rule("p(X1, Y1) ^ p(X2, Y2) => gh(X1, Y1, X2, Y2)"), mergedRule);
   }
   
   @Test
   public void merge_non_contradictory_solutions_returns_cartesian_product() throws Exception{
      merge(rule("p(X, Y) => g(X, Y)"), 
            solutions(true, solution(replacement("X", "bob"), replacement("Y", "joe"))),
            
            rule("p(X, Y) => h(X, Y)"), 
            solutions(true, solution(replacement("X", "gary"), replacement("Y", "mark"))));
      
      assertEquals(solutions(true, solution(replacement("X1", "bob"), replacement("Y1", "joe"), replacement("X2", "gary"), replacement("Y2", "mark"))), mergedSolutions);
      assertEquals(rule("p(X1, Y1) ^ p(X2, Y2) => gh(X1, Y1, X2, Y2)"), mergedRule);
   }

   private void merge(Rule rule1, SolutionSet solutions1, Rule rule2, SolutionSet solutions2) {
      ConsistentMerge consistentMerge = new ConsistentMerge(rule1, solutions1, rule2, solutions2);
      consistentMerge.ignoring(Collections.<Statement>emptyList());
      mergedSolutions = consistentMerge.getMergedSolutions();
      mergedRule = consistentMerge.getMergedRule();
   }

}
