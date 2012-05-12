package com.gooble.logic.puzzle;

import java.util.List;

import com.gooble.logic.kb.KnowledgeBaseFacade;
import com.gooble.logic.kb.Rule;
import com.gooble.logic.kb.stmts.Statement;

public class Solver{

   private final KnowledgeBaseFacade kb;

   public Solver(KnowledgeBaseFacade kb) {
      this.kb = kb;
   }

   public void augment(Definition... defns) {
      for (Definition defn : defns){
         defn.augment(kb);
      }
   }

   public void merge(MergerFacade merger, List<Statement> ignoreList, List<Rule> mergingRules) {
      for (Rule rule : mergingRules){
         merger.mergeWith(rule, kb.findSolutions(rule.getConsequence()), ignoreList);
      }
   }
   
}