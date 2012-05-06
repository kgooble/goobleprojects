package com.gooble.logic.kb;

import java.util.ArrayList;
import java.util.List;

import com.gooble.logic.kb.solutions.Solution;
import com.gooble.logic.kb.stmts.Statement;
import com.gooble.logic.kb.terms.Term;
import com.gooble.logic.kb.terms.Variable;

public class Suffix {

   public static Term<?>[] terms(Term<?>[] terms, String suffix) {
      Term<?>[] suffixedTerms = new Term<?>[terms.length];
      for (int i = 0; i < terms.length; i++){
         suffixedTerms[i] = terms[i].copyWithSuffix(suffix);
      }
      return suffixedTerms;
   }
   
   public static List<Solution> solutions(List<Solution> oldSolns, String suffix){
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

   public static Statement statement(Statement statement, String suffix) {
      return new Statement(statement.getName() + suffix, statement.getTerms());
   }

}
