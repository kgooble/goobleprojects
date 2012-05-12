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
import com.gooble.logic.kb.stmts.Statement;
import com.gooble.logic.puzzle.Definition;
import com.gooble.logic.puzzle.Solver;
import com.gooble.logic.test.puzzle.stubs.DefinitionStub;
import com.gooble.logic.test.puzzle.stubs.KBStub;
import com.gooble.logic.test.puzzle.stubs.MergerStub;

public class SolverTest {
   private KBStub kbStub;
   private Solver solver;

   @Before
   public void init() throws Exception{
      kbStub = new KBStub();
      solver = new Solver(kbStub);
   }
   @Test
   public void test_augment_uses_given_definitions() throws Exception {
      Rule fakeRule = rule("p(X) => g(X)");
      Definition defStub = new DefinitionStub(fakeRule);
      
      solver.augment(defStub, defStub, defStub);
      
      assertEquals(Arrays.asList(fakeRule, fakeRule, fakeRule), kbStub.rules);
   }
   
   @Test
   public void merge_given_rules_using_merger_and_given_ignore_list() throws Exception{
      MergerStub mergerStub = new MergerStub();
      List<Statement> ignoreList = Arrays.asList(statement("q(X, Y)"));
      List<Rule> mergingRules = Arrays.asList(
            rule("p(X, A) ^ g(X) ^ r(A) => h(A, X)"), 
            rule("h(X) ^ m(X) => n(X)"));
      
      solver.merge(mergerStub, ignoreList, mergingRules);

      assertEquals(Arrays.asList(ignoreList, ignoreList), mergerStub.ignoredListsInOrder);
      assertEquals(mergingRules, mergerStub.mergedRulesInOrder);
      assertEquals(Arrays.asList(
            solutions(true, solution(replacement("h[VAR:A, VAR:X]", 1))),
            solutions(true, solution(replacement("n[VAR:X]", 1)))
            ), mergerStub.mergedSolnsInOrder);
   }
}
