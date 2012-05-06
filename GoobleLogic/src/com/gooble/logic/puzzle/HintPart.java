package com.gooble.logic.puzzle;

import static com.gooble.logic.kb.encoding.KBEncoding.statement;

import com.gooble.logic.TwoObjectsEqual;
import com.gooble.logic.kb.stmts.Statement;

public abstract class HintPart{
   abstract public String toString();
   public Statement toStatement() {
      return statement(toString());
   }
   @Override
   public boolean equals(Object other){
      return TwoObjectsEqual.byReflection(this, other);
   }
}