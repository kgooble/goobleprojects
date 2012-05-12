package com.gooble.logic.test.puzzle;

import static com.gooble.logic.kb.encoding.KBEncoding.replacement;
import static com.gooble.logic.kb.encoding.KBEncoding.rule;
import static com.gooble.logic.kb.encoding.KBEncoding.solution;
import static com.gooble.logic.kb.encoding.KBEncoding.solutions;
import static com.gooble.logic.kb.encoding.KBEncoding.statement;
import static junit.framework.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.gooble.logic.kb.Rule;
import com.gooble.logic.kb.solutions.SolutionSet;
import com.gooble.logic.kb.stmts.Statement;
import com.gooble.logic.puzzle.Merger;
import com.gooble.logic.puzzle.MergerFacade;

public class MergerTest {

   private List<Statement> ignoreList;
   private SolutionSet solutions;
   private Rule rule;
   private MergerFacade merger;

   @Before
   public void init() throws Exception{
      merger = new Merger();
      rule = rule("p(X) => g(X)");
      solutions = solutions(true, solution(replacement("X", 1)));
      ignoreList = Arrays.asList(statement("p(X)"));
   }
   @Test
   public void first_time_merging_sets_variables() throws Exception {
      merger.mergeWith(rule, solutions, ignoreList);
      assertEquals(rule, merger.getMergedRule());
      assertEquals(solutions, merger.getMergedSolutions());
   }
   @Test
   public void second_time_merging_sets_merged_rule_and_solns_as_merged_values() throws Exception {
      merger.mergeWith(rule, solutions, ignoreList);
      merger.mergeWith(rule, solutions, ignoreList);
      assertEquals(rule("p(X1) ^ p(X2) => gg(X1, X2)"), merger.getMergedRule());
      assertEquals(
            solutions(true, solution(replacement("X1", 1), replacement("X2", 1))),
            merger.getMergedSolutions());
   }
}
