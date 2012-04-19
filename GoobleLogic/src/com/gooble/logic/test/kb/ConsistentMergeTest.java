package com.gooble.logic.test.kb;

import static com.gooble.logic.kb.KBEncoding.replacement;
import static com.gooble.logic.kb.KBEncoding.rule;
import static com.gooble.logic.kb.KBEncoding.solution;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

import com.gooble.logic.kb.ConsistentMerge;
import com.gooble.logic.kb.Rule;
import com.gooble.logic.kb.solutions.SolutionSet;
import com.gooble.logic.kb.stmts.Statement;

public class ConsistentMergeTest {
   
   @Test
   public void merge_contradictory_solutions_result_in_empty_set() throws Exception{
      Rule rule1 = rule("p(X, Y) => g(X, Y)");
      Rule rule2 = rule("p(X, Y) => h(X, Y)");
      SolutionSet solutions1 = new SolutionSet(Arrays.asList(solution(replacement("X", "bob"), replacement("Y", "joe"))), true);
      SolutionSet solutions2 = new SolutionSet(Arrays.asList(solution(replacement("X", "bob"), replacement("Y", "mark"))), true);
      SolutionSet mergedSolutions = new ConsistentMerge(rule1, solutions1, rule2, solutions2).ignoring(Collections.<Statement>emptyList());
      assertFalse(mergedSolutions.hasSolutions());
   }
   
   @Test
   public void merge_non_contradictory_solutions_returns_cartesian_product() throws Exception{
      Rule rule1 = rule("p(X, Y) => g(X, Y)");
      Rule rule2 = rule("p(X, Y) => h(X, Y)");
      SolutionSet solutions1 = new SolutionSet(Arrays.asList(solution(replacement("X", "bob"), replacement("Y", "joe"))), true);
      SolutionSet solutions2 = new SolutionSet(Arrays.asList(solution(replacement("X", "gary"), replacement("Y", "mark"))), true);
      SolutionSet mergedSolutions = new ConsistentMerge(rule1, solutions1, rule2, solutions2).ignoring(Collections.<Statement>emptyList());
      assertEquals(Arrays.asList(solution(replacement("X1", "bob"), replacement("Y1", "joe"), replacement("X2", "gary"), replacement("Y2", "mark"))), mergedSolutions.list());
   }

}
