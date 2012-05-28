package com.gooble.logic.kb.stmts;

import java.util.ArrayList;
import java.util.List;

import com.gooble.logic.kb.Replacement;
import com.gooble.logic.kb.Rule;
import com.gooble.logic.kb.solutions.Solution;

public class SolvedStatements {

   public List<Statement> createFromAntecedents(Rule rule, Solution soln){
      Statement[] antecedents = rule.getAntecedents();
      
      List<Replacement> replacements = soln.getReplacements();
      
      List<Statement> solvedStmts = new ArrayList<Statement>();
      for (Statement stmt : antecedents){
         solvedStmts.add(stmt.applyReplacements(replacements));
      }
      return solvedStmts;
   }
   
}
