package com.gooble.logic.test.puzzle.stubs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.gooble.logic.kb.Rule;
import com.gooble.logic.kb.solutions.SolutionSet;
import com.gooble.logic.kb.stmts.Statement;
import com.gooble.logic.puzzle.MergerFacade;

public class MergerStub implements MergerFacade{
   public final List<Rule> mergedRulesInOrder = new ArrayList<Rule>();
   public final List<SolutionSet> mergedSolnsInOrder = new ArrayList<SolutionSet>();
   public final List<Collection<Statement>> ignoredListsInOrder = new ArrayList<Collection<Statement>>();
   @Override
   public void mergeWith(Rule rule, SolutionSet soln, Collection<Statement> ignoreList) {
      mergedRulesInOrder.add(rule);
      mergedSolnsInOrder.add(soln);
      ignoredListsInOrder.add(ignoreList);
   }
   @Override
   public Rule getMergedRule() {
      return null;
   }
   @Override
   public SolutionSet getMergedSolutions() {
      return null;
   }
}
