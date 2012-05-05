package com.gooble.logic.puzzle;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.gooble.logic.kb.stmts.Statement;
import com.gooble.logic.kb.terms.Variable;

public class StatementVariables{

   private final List<Statement> statements;

   public StatementVariables(List<Statement> statements) {
      this.statements = statements;
   }

   public Set<Variable> find() {
      Set<Variable> foundVariables = new HashSet<Variable>();
      for (Statement statement : statements){
         foundVariables.addAll(statement.getVariables());
      }
      return foundVariables;
   }
   
}