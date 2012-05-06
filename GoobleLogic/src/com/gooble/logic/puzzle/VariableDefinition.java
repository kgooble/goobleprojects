package com.gooble.logic.puzzle;

import com.gooble.logic.kb.KnowledgeBaseFacade;
import com.gooble.logic.puzzle.domain.DomainRules;
import com.gooble.logic.puzzle.domain.DomainStatements;
import com.gooble.logic.puzzle.domain.DomainVariables;

public class VariableDefinition implements Definition {
   private DomainVariables variables;

   public VariableDefinition() {
      variables = new DomainVariables();
   }
   
   public void add(String varName, Object... domainValues) {
      variables.put(varName, domainValues);
   }

   public void setMain(String varName) {
      variables.setMain(varName);
   }

   public void augment(KnowledgeBaseFacade kb) {
      addStatements(kb);
      addRules(kb);
      addSolutions(kb);
   }

   private void addStatements(KnowledgeBaseFacade kb) {
      new DomainStatements(variables).add(kb);
   }

   private void addRules(KnowledgeBaseFacade kb) {
      new DomainRules(variables).forProperties(kb);
   }

   private void addSolutions(KnowledgeBaseFacade kb) {
      new DomainRules(variables).forSolution(kb);
   }
}
