package com.gooble.logic.kb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.gooble.logic.kb.solutions.ContradictingSolution;
import com.gooble.logic.kb.solutions.Solution;
import com.gooble.logic.kb.solutions.SolutionSet;
import com.gooble.logic.kb.stmts.Statement;

public class ConsistentMerge {

   private final Rule rule1;
   private final SolutionSet solutions2;
   private final Rule rule2;
   private final SolutionSet solutions1;
   private SolutionSet mergedSolutions;
   private Rule mergedRule;

   public ConsistentMerge(Rule rule1, SolutionSet solutions1, Rule rule2, SolutionSet solutions2) {
      this.rule1 = rule1;
      this.solutions1 = solutions1;
      this.rule2 = rule2;
      this.solutions2 = solutions2;
   }

   public void ignoring(Collection<Statement> ignoreList) {
      List<Solution> mergedSolutions = new ArrayList<Solution>();
      mergedRule = new Rules(rule1, rule2).merge();
      mergeSolutions(ignoreList, mergedSolutions);
      this.mergedSolutions = new SolutionSet(mergedSolutions, solutions1.isQueryTrue() && solutions2.isQueryTrue() && !mergedSolutions.isEmpty());
   }

   private void mergeSolutions(Collection<Statement> ignoreList, List<Solution> mergedSolutions) {
      List<Solution> newSolns1 = Suffix.solutions(solutions1.list(), "1");
      List<Solution> newSolns2 = Suffix.solutions(solutions2.list(), "2");
      for (Solution s1 : newSolns1){
         for (Solution s2 : newSolns2){
            mergeIfNotContradictory(ignoreList, mergedSolutions, s1, s2);
         }
      }
   }

   private void mergeIfNotContradictory(Collection<Statement> ignoreList, List<Solution> mergedSolutions, Solution s1, Solution s2) {
      Solution soln = new Solution();
      soln.addReplacements(s1.getReplacements());
      soln.addReplacements(s2.getReplacements());
      if (isNotContradictory(soln, mergedRule, ignoreList)){
         mergedSolutions.add(soln);
      }
   }
   
   public Rule getMergedRule(){
      return mergedRule;
   }
   public SolutionSet getMergedSolutions(){
      return mergedSolutions;
   }

   private boolean isNotContradictory(Solution soln, Rule rule, Collection<Statement> ignoreList) {
      return !new ContradictingSolution(soln).isContradictoryBasedOnRule(rule, ignoreList);
   }
   
   @Override
   public String toString(){
      return mergedRule + "\n" + mergedSolutions;
   }

}
