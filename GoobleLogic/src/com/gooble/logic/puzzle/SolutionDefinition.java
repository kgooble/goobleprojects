package com.gooble.logic.puzzle;

import java.util.ArrayList;
import java.util.List;

import com.gooble.logic.kb.stmts.Statement;

public class SolutionDefinition {

   private final List<Statement> statements;
   private final String[] expectedOrder;

   public SolutionDefinition(String... expectedOrder) {
      this.expectedOrder = expectedOrder;
      this.statements = new ArrayList<Statement>();
   }

   public void add(String mainValue, Object... theRest) {
      int i = 1;
      for (Object value : theRest){
         statements.add(new Property(expectedOrder[i], mainValue, value).toStatement());
         i++;
      }
   }

   public List<Statement> getStatements() {
      return statements;
   }

}
