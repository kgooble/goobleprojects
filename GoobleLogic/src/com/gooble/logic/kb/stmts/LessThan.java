package com.gooble.logic.kb.stmts;

import com.gooble.logic.kb.Constant;
import com.gooble.logic.kb.Term;


public class LessThan extends Operator {

   public LessThan(Term<?> limit, Term<?> constant) {
      super("<", limit, constant);
   }
   
   @Override
   public Statement createCopyWithTerms(Term<?>[] constants){
      return new LessThan(constants[0], constants[1]);
   }

   @Override
   protected boolean evaluateInternal(Constant<Number> limit, Constant<Number> constant) {
      return ((Number) constant.getValue()).floatValue() < ((Number) limit.getValue()).floatValue();
   }

}
