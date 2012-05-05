package com.gooble.logic.puzzle;

import static com.gooble.logic.kb.encoding.KBEncoding.statement;

import com.gooble.logic.TwoObjectsEqual;
import com.gooble.logic.kb.stmts.Statement;


public class Property implements HintPart{
   private final String name;
   private final String owner;
   private final String value;
   public Property(String name, String owner, String value){
      this.name = name;
      this.owner = owner;
      this.value = value;
   }
   @Override
   public Statement toStatement() {
      return statement(name + "Of(" + owner + ", " + value + ")");
   }
   @Override
   public boolean equals(Object other){
      return TwoObjectsEqual.byReflection(this, other);
   }
   @Override
   public String toString(){
      return "Property: " + name + " of " + owner + " is " + value;
   }
}