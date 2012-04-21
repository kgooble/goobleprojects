package com.gooble.logic.kb.stmts;

import com.gooble.logic.kb.terms.Constant;
import com.gooble.logic.kb.terms.Term;


public class GreaterThan extends Operator{

   public GreaterThan(Term<?> limit, Term<?> constant) {
      super(">", limit, constant);
   }
   
   @Override
   public Statement createCopyWithTerms(Term<?>[] constants){
      return new GreaterThan(constants[0], constants[1]);
   }

   @Override
   protected boolean evaluateInternal(Constant<Number> limit, Constant<Number> constant) {
      return ((Number) constant.getValue()).floatValue() > limit.getValue().floatValue();
   }
   
}