package com.gooble.logic.puzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.gooble.logic.kb.stmts.Statement;
import com.gooble.logic.kb.terms.Variable;

public class StatementFrom {
   private final String statementName;

   public StatementFrom(String statementName) {
      this.statementName = statementName;
   }

   public Statement numberOfVariables(int n){
      List<Variable> variables = new ArrayList<Variable>();
      for (int i = 1; i <= n; i++){
         variables.add(new Variable("V" + i));
      }
      return new Statement(statementName, variables.toArray(new Variable[n]));
   }

   public Statement variables(Object mainVal, Set<String> variableNames) {
      List<Variable> variables = new ArrayList<Variable>();
      for (String varName : variableNames){
         variables.add(new Variable((mainVal + varName).toUpperCase()));
      }
      return new Statement(statementName, variables.toArray(new Variable[variableNames.size()]));
   }
}
