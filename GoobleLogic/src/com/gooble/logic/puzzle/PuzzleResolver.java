package com.gooble.logic.puzzle;

import java.util.List;

import com.gooble.logic.kb.Replacement;
import com.gooble.logic.kb.Rule;
import com.gooble.logic.kb.solutions.Solution;
import com.gooble.logic.kb.solutions.SolutionSet;
import com.gooble.logic.kb.stmts.SolvedStatements;
import com.gooble.logic.kb.stmts.Statement;

public class PuzzleResolver{

   private final SolutionDefinition solnDef;
   private final static boolean INCONSISTENT = false;
   private final static boolean CONSISTENT = true;

   public PuzzleResolver(SolutionDefinition solnDef) {
      this.solnDef = solnDef;
   }

   public ResolveResult resolveSolutions(Rule rule, SolutionSet solnSet) {
      Statement[] antecedents = rule.getAntecedents();
      
      List<Solution> solns = solnSet.getSolutions();
      List<Replacement> replacements = solns.get(0).getReplacements();
      
      List<Statement> solvedStmts = new SolvedStatements().createFromAntecedents(rule, solns.get(0));
      for (Statement stmt : antecedents){
         solvedStmts.add(stmt.applyReplacements(replacements));
      }
      
      for (Statement stmt : solnDef.getStatements()){
         if (!solvedStmts.contains(stmt)){
            return new ResolveResult(INCONSISTENT);
         }
      }
      
      return new ResolveResult(CONSISTENT);
   }
   
}