package com.gooble.logic.kb.solutions;

import java.util.ArrayList;
import java.util.List;

import com.gooble.logic.kb.Replacement;
import com.gooble.logic.kb.terms.Variable;

public class SolutionSuffixer {

   public static List<Solution> suffix(List<Solution> oldSolns, String suffix){
      List<Solution> newSolns = new ArrayList<Solution>();

      for (Solution s : oldSolns){
         Solution soln = new Solution();
         for (Replacement re : s.getReplacements()){
            soln.addVariableReplacement(new Replacement(new Variable(re.getVariable().getValue() + suffix), re.getValue()));
         }
         newSolns.add(soln);
      }
      return newSolns;
   }

}
