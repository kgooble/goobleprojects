package com.gooble.logic.kb.solutions;

import java.util.ArrayList;
import java.util.List;

import com.gooble.logic.kb.Replacement;


public class Normalize {
   
   private final List<Replacement> originalReplacements;

   public Normalize(List<Replacement> originalReplacements) {
      this.originalReplacements = originalReplacements;
   }

   public SolutionSet solutions(SolutionSet solution){
      List<Solution> properSolns = new ArrayList<Solution>();
      SolutionSet newSolns = new SolutionSet(properSolns, solution.isQueryTrue());
      for (Solution soln : solution.list()){
         properSolns.add(normalized(soln));
      }
      return newSolns;
   }

   private Solution normalized(Solution soln) {
      Solution properSoln = new Solution();
      for (Replacement re : soln.getReplacements()){
         normalizeReplacement(properSoln, re);
      }
      return properSoln;
   }

   private void normalizeReplacement(Solution properSoln, Replacement re) {
      for (Replacement originalReplacement : originalReplacements){
         if (re.getVariable().equals(originalReplacement.getValue())){
            properSoln.addVariableReplacement(new Replacement(originalReplacement.getVariable(), re.getValue()));
            break;
         }
      }
   }

}
