package com.gooble.logic.kb.solutions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.gooble.logic.kb.Rule;
import com.gooble.logic.kb.stmts.Statement;

public class ContradictingSolution {

   private final Solution soln;

   public ContradictingSolution(Solution soln) {
      this.soln = soln;
   }
   
   public boolean isContradictoryBasedOnRule(Rule rule, Collection<Statement> ignoreList){
      Statement[] antes = rule.getAntecedents();
      List<Statement> resolvedAntes = new ArrayList<Statement>(antes.length);
      for (Statement ante : antes) {
         resolvedAntes.add(ante.applyReplacements(soln.getReplacements()));
      }
      boolean thisOneIsBad = false;
      for (int i = 0; i < resolvedAntes.size(); i++) {
         for (int j = 0; j < resolvedAntes.size(); j++) {
            if (j == i) continue;
            if (oneSameValueButRestDifferent(resolvedAntes.get(i), resolvedAntes.get(j)) 
                  && !inIgnoreList(resolvedAntes.get(i), ignoreList)){
               thisOneIsBad = true;
               break;
            }
         }
         if (thisOneIsBad) break;
      }
      return thisOneIsBad;
   }
   private boolean oneSameValueButRestDifferent(Statement first, Statement second) {
      if (first.getName().equals(second.getName()) && first.getTerms().length == second.getTerms().length){
         boolean allMatch = false;
         boolean onlySomeMatch = false;
         for (int i = 0; i < first.getTerms().length; i++){
            if (first.getTerms()[i].match(second.getTerms()[i])){
               allMatch = true;
            } else{
               if (allMatch)
                  onlySomeMatch = true;
            }
         }
         return onlySomeMatch;
      }
      
      return false;
   }
   private boolean inIgnoreList(Statement statement, Collection<Statement> ignoreList) {
      for (Statement s : ignoreList){
         if (s.match(statement)) 
            return true;
      }
      return false;
   }
}
