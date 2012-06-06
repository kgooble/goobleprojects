package com.gooble.logic.puzzle;

import com.gooble.logic.Logger;
import com.gooble.logic.kb.KnowledgeBase;
import com.gooble.logic.kb.KnowledgeBaseFacade;
import com.gooble.logic.kb.solutions.SolutionSet;

public class Puzzle{

   private final VariableDefinition varDef;
   private final HintDefinition hintDef;
   private final SolutionDefinition solnDef;
   private final RelationDefinition relDef;

   public Puzzle(VariableDefinition varDef, HintDefinition hintDef, SolutionDefinition solnDef, RelationDefinition relDef) {
      this.varDef = varDef;
      this.hintDef = hintDef;
      this.solnDef = solnDef;
      this.relDef = relDef;
   }

   public Result solve() {
      KnowledgeBaseFacade kb = new KnowledgeBase();
      new Solver(kb).augment(varDef, hintDef, relDef);
      Merger merger = getFinalSolutions(kb);
      SolutionSet solutions = merger.getMergedSolutions();
      
      Logger.log("Final merged solutions: ");
      Logger.log(solutions.toString());
      
      return new Result(solutions.isQueryTrue() && new PuzzleResolver(solnDef).resolveSolutions(merger.getMergedRule(), solutions).isPuzzleConsistent());
   }

   private Merger getFinalSolutions(KnowledgeBaseFacade kb) {
      Merger merger = new Merger();
      new Solver(kb).merge(merger, relDef.getNonUniqueStatements(), hintDef.getHintRules());
      new Solver(kb).merge(merger, relDef.getNonUniqueStatements(), varDef.getSolutionRules());
      return merger;
   }
   
}