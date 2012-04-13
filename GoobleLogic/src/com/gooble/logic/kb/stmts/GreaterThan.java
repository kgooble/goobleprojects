package com.gooble.logic.kb.stmts;

import com.gooble.logic.kb.Constant;
import com.gooble.logic.kb.Statement;
import com.gooble.logic.kb.Term;


public class GreaterThan extends Operator{

   public GreaterThan(Term<?> limit, Term<?> constant) {
      super(">", limit, constant);
   }
   
   @Override
   protected Statement createStatement(Term<?>[] constants){
      return new GreaterThan(constants[0], constants[1]);
   }

   @Override
   protected boolean evaluateInternal(Constant<Number> limit, Constant<Number> constant) {
      return ((Number) constant.getValue()).floatValue() > limit.getValue().floatValue();
   }
   
}