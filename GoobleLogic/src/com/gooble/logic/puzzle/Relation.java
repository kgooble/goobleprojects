package com.gooble.logic.puzzle;

import static com.gooble.logic.kb.encoding.KBEncoding.statement;

import com.gooble.logic.TwoObjectsEqual;
import com.gooble.logic.kb.stmts.Statement;


public class Relation implements HintPart{
   private final String name;
   private final String term1;
   private final String term2;
   public Relation(String name, String term1, String term2) {
      this.name = name;
      this.term1 = term1;
      this.term2 = term2;
   }
   public Statement toStatement() {
      return statement(name + "(" + term1 + ", " + term2 + ")");
   }
   @Override
   public boolean equals(Object other){
      return TwoObjectsEqual.byReflection(this, other);
   }
   @Override
   public String toString(){
      return "Relation: " + term1 + " is " + name + " " + term2;
   }
}