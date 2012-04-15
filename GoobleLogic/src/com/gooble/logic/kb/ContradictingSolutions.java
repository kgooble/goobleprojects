package com.gooble.logic.kb;

import java.util.ArrayList;
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
      List<Replacement> originalReplacements = rule.getConsequence().unifyWith(statement);
      List<Solution> normalizedSolns = new SolutionNormalizer(originalReplacements).normalize(new SolutionSet(solns, true)).getSolutions();
      List<Solution> goodOnes = new ArrayList<Solution>();
      for (Solution s : normalizedSolns) {
         filterSolution(goodOnes, s);
      }
      originalReplacements = statement.unifyWith(rule.getConsequence());
      return new SolutionNormalizer(originalReplacements).normalize(new SolutionSet(goodOnes, true)).getSolutions();
   }

   private void filterSolution(List<Solution> goodOnes, Solution s) {
      Statement[] antes = rule.getAntecedents();
      List<Statement> resolvedAntes = new ArrayList<Statement>(antes.length);
      for (Statement ante : antes) {
         resolvedAntes.add(ante.applyReplacements(s.getReplacements()));
      }
      boolean thisOneIsBad = false;
      for (int i = 0; i < resolvedAntes.size(); i++) {
         for (int j = 0; j < resolvedAntes.size(); j++) {
            if (j == i) continue;
            if (oneSameValueButRestDifferent(resolvedAntes.get(i), resolvedAntes.get(j))){
               thisOneIsBad = true;
               break;
            }
         }
         if (thisOneIsBad) break;
      }
      if (!thisOneIsBad){
         goodOnes.add(s);
      }
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

}
