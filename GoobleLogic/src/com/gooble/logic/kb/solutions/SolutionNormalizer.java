package com.gooble.logic.kb.solutions;

import java.util.ArrayList;
import java.util.List;

import com.gooble.logic.kb.Replacement;


public class SolutionNormalizer {
   
   private final List<Replacement> originalReplacements;

   public SolutionNormalizer(List<Replacement> originalReplacements) {
      this.originalReplacements = originalReplacements;
   }

   public SolutionSet normalize(SolutionSet solution){
      List<Solution> properSolns = new ArrayList<Solution>();
      SolutionSet newSolns = new SolutionSet(properSolns, solution.isQueryTrue());
      for (Solution soln : solution.getSolutions()){
         properSolns.add(normalizeSolution(soln));
      }
      return newSolns;
   }

   private Solution normalizeSolution(Solution soln) {
      Solution properSoln = new Solution();
      for (Replacement re : soln.getReplacements()){
         nroamlizeReplacement(properSoln, re);
      }
      return properSoln;
   }

   private void nroamlizeReplacement(Solution properSoln, Replacement re) {
      for (Replacement originalReplacement : originalReplacements){
         if (re.getVariable().equals(originalReplacement.getValue())){
            properSoln.addVariableReplacement(new Replacement(originalReplacement.getVariable(), re.getValue()));
            break;
         }
      }
   }

}
