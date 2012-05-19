package com.gooble.logic.puzzle;

import java.util.List;

import com.gooble.logic.kb.KnowledgeBaseFacade;
import com.gooble.logic.kb.Rule;
import com.gooble.logic.puzzle.domain.DomainRules;
import com.gooble.logic.puzzle.domain.DomainStatements;
import com.gooble.logic.puzzle.domain.DomainVariables;

public class VariableDefinition implements Definition {
   private DomainVariables variables;
   private List<Rule> solutionRules;

   public VariableDefinition() {
      variables = new DomainVariables();
   }
   
   public void add(String type, Object... domainValues) {
      variables.put(type, domainValues);
   }

   public void setMain(String type) {
      variables.setMain(type);
   }

   public void augment(KnowledgeBaseFacade kb) {
      new DomainStatements(variables).forValues(kb);
      new DomainRules(variables).forProperties(kb);
      solutionRules = new DomainRules(variables).forSolution(kb);
   }

   public boolean isMain(String type) {
      return type.equals(variables.getMain());
   }
   
   public List<Rule> getSolutionRules(){
      return solutionRules;
   }
}
