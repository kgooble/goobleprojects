package com.gooble.logic.kb;

import com.gooble.logic.kb.stmts.Operator;
import com.gooble.logic.kb.stmts.Statement;
import com.gooble.logic.kb.terms.Constant;
import com.gooble.logic.kb.terms.Term;

public class Equals extends Operator{

   public Equals(Term<?> limit, Term<?> constant) {
      super("=", limit, constant);
   }

   @Override
   public Statement createCopyWithTerms(Term<?>[] constants) {
      return new Equals(constants[0], constants[1]);
   }

   @Override
   protected boolean evaluateInternal(Constant<Number> limit, Constant<Number> constant) {
      return limit.equals(constant);
   }

}
