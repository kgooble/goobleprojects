package com.gooble.logic.puzzle.domain;

import com.gooble.logic.kb.KnowledgeBaseFacade;
import com.gooble.logic.kb.stmts.Statement;
import com.gooble.logic.kb.terms.Constant;
import com.gooble.logic.kb.terms.Term;

public class DomainStatements {
   private final DomainVariables variables;
   public DomainStatements(DomainVariables variables) {
      this.variables = variables;
   }
   public void add(KnowledgeBaseFacade kb){
      for (String varName : variables.types()){
         for (Object domainVal : variables.values(varName)){
            Term<?> term = new Constant<Object>(domainVal);
            kb.add(new Statement(varName, term));
         }
      }
   }
   
}
