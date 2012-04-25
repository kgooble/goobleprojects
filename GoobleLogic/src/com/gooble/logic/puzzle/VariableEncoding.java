package com.gooble.logic.puzzle;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gooble.logic.kb.KnowledgeBaseFacade;
import com.gooble.logic.kb.Rule;
import com.gooble.logic.kb.stmts.Statement;
import com.gooble.logic.kb.terms.Constant;
import com.gooble.logic.kb.terms.Term;
import com.gooble.logic.kb.terms.Variable;

public class VariableEncoding {
   private Map<String, List<?>> variables = new HashMap<String, List<?>>();
   private String main;

   public void add(String varName, Object... domainValues) {
      variables.put(varName, Arrays.asList(domainValues));
   }

   public void setMain(String varName) {
      main = varName;
   }

   public void augment(KnowledgeBaseFacade kb) {
      addStatements(kb);
      addRules(kb);
   }

   private void addStatements(KnowledgeBaseFacade kb) {
      for (String varName : variables.keySet()){
         for (Object domainVal : variables.get(varName)){
            Term<?> term = new Constant<Object>(domainVal);
            kb.add(new Statement(varName, term));
         }
      }
   }

   private void addRules(KnowledgeBaseFacade kb) {
      for (String varName : variables.keySet()){
         if (varName.equals(main)) 
            continue;
         kb.add(new Rule(
               new Statement(varName + "Of", new Variable[]{new Variable("X"), new Variable("Y")}), 
               new Statement(main, new Variable("X")), new Statement(varName, new Variable("Y"))));
      }
   }
}
