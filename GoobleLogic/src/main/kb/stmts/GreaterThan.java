package main.kb.stmts;

import main.kb.Constant;
import main.kb.Statement;

public class GreaterThan extends Statement{

   private Constant<?> constant;
   private Constant<Number> limit;

   public GreaterThan(Constant<Number> limit, Constant<?> constant) {
      super("greaterThan", limit, constant);
      this.limit = limit;
      this.constant = constant;
   }
   
   @Override
   public String toString(){
      return "(" + limit + " > " + constant + ")"; // constant > limit
   }
   
   @Override
   public boolean match(Statement other){
      return super.match(other);
   }
   
   @Override
   public boolean isToBeEvaluated() {
      return true;
   }
   
   @Override
   public boolean evaluate(){
      if (!(constant.getValue() instanceof Number))
         return false;
      else
         return ((Number) constant.getValue()).floatValue() > limit.getValue().floatValue(); 
   }
   
   @Override
   protected Statement createStatement(Constant<?>[] constants){
      return new GreaterThan((Constant<Number>) constants[0], constants[1]);
   }
   
}