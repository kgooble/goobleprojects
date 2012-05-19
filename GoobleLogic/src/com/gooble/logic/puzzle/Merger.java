package com.gooble.logic.puzzle;

import java.util.Collection;

import com.gooble.logic.kb.ConsistentMerge;
import com.gooble.logic.kb.Rule;
import com.gooble.logic.kb.solutions.SolutionSet;
import com.gooble.logic.kb.stmts.Statement;

public class Merger implements MergerFacade{

   private Rule mergedRule;
   private SolutionSet mergedSolns;
   
   @Override
   public void mergeWith(Rule rule, SolutionSet solutions, Collection<Statement> ignoreList) {
      if (didNotPreviouslyMerge()){
         assignMergedToNew(rule, solutions);
      }else{
         mergeWithOld(rule, solutions, ignoreList);
      }
   }

   private boolean didNotPreviouslyMerge() {
      return mergedRule == null && mergedSolns == null;
   }

   private void mergeWithOld(Rule rule, SolutionSet solutions, Collection<Statement> ignoreList) {
      ConsistentMerge merge = new ConsistentMerge(mergedRule, mergedSolns, rule, solutions);
      merge.ignoring(ignoreList);
      mergedRule = merge.getMergedRule();
      mergedSolns = merge.getMergedSolutions();
   }
   
   private void assignMergedToNew(Rule rule, SolutionSet solutions) {
      mergedRule = rule;
      mergedSolns = solutions;
   }

   @Override
   public Rule getMergedRule() {
      return mergedRule;
   }

   @Override
   public SolutionSet getMergedSolutions() {
      return mergedSolns;
   }
   
}