package com.gooble.logic.kb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.gooble.logic.kb.solutions.Solution;
import com.gooble.logic.kb.solutions.SolutionNormalizer;
import com.gooble.logic.kb.solutions.SolutionSet;
import com.gooble.logic.kb.stmts.Statement;

public class ContradictingSolutions {

   private final List<Solution> solns;
   private final Rule rule;
   private final Statement statement;

   public ContradictingSolutions(Rule rule, Statement statement, List<Solution> solns) {
      this.rule = rule;
      this.statement = statement;
      this.solns = solns;
   }

   public List<Solution> remove() {
      return removeIgnoring(Collections.<Statement>emptyList());
   }

   public List<Solution> removeIgnoring(List<Statement> ignoreList) {
      List<Replacement> originalReplacements = rule.getConsequence().unifyWith(statement);
      List<Solution> normalizedSolns = new SolutionNormalizer(originalReplacements).normalize(new SolutionSet(solns, true)).list();
      List<Solution> goodOnes = new ArrayList<Solution>();
      for (Solution s : normalizedSolns) {
         filterSolution(goodOnes, s, ignoreList);
      }
      originalReplacements = statement.unifyWith(rule.getConsequence());
      return new SolutionNormalizer(originalReplacements).normalize(new SolutionSet(goodOnes, true)).list();
   }
   
   private void filterSolution(List<Solution> goodOnes, Solution s, List<Statement> ignoreList) {
      boolean thisOneIsBad = new ContradictingSolution(s).isContradictoryBasedOnRule(rule, ignoreList);
      if (!thisOneIsBad){
         goodOnes.add(s);
      }
   }

}
