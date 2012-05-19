package com.gooble.logic.puzzle;

import java.util.List;

import com.gooble.logic.kb.KnowledgeBase;
import com.gooble.logic.kb.KnowledgeBaseFacade;
import com.gooble.logic.kb.Rule;

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
      
      Merger merger = new Merger();
      new Solver(kb).merge(merger, relDef.getNonUniqueStatements(), makeHintRules());
      new Solver(kb).merge(merger, relDef.getNonUniqueStatements(), makeSolutionRules());
      
      return new Result(merger.getMergedSolutions().isQueryTrue());
   }

   private List<Rule> makeSolutionRules(){
      return varDef.getSolutionRules();
   }
   private List<Rule> makeHintRules() {
      return hintDef.getHintRules();
   }
   
}