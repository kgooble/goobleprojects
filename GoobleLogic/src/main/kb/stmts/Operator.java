package main.kb.stmts;

import main.kb.Constant;
import main.kb.Statement;

abstract public class Operator extends Statement {

   private final Constant<?> constant;
   private final Constant<Number> limit;
   
   public Operator(String name, Constant<Number> limit, Constant<?> constant) {
      super(name, limit, constant);
      this.limit = limit;
      this.constant = constant;
   }
   
   @Override
   public boolean evaluate(){
      if (!(constant.getValue() instanceof Number))
         return false;
      else
         return evaluateInternal(limit, (Constant<Number>) constant);
   }
   
   abstract protected boolean evaluateInternal(Constant<Number> limit, Constant<Number> constant);
   
   @Override
   public boolean isToBeEvaluated() {
      return true;
   }
   
   @Override
   protected Statement createStatement(Constant<?>[] constants){
      throw new UnsupportedOperationException("Subclass of operator has not overridden create statement method!");
   }
   
   @Override
   public String toString(){
      return "(" + limit + " " + name + " " + constant + ")";
   }

}
