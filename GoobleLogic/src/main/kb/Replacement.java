package main.kb;

public class Replacement {

   private final Constant<?> constant;
   private final Variable variable;

   public Replacement(Variable variable, Constant<?> replacement) {
      this.variable = variable;
      this.constant = replacement;
   }
   
   @Override
   public boolean equals(Object obj){
      if (!(obj instanceof Replacement))
         return false;
      Replacement other = (Replacement) obj;
      return other.variable.equals(this.variable) && other.constant.equals(this.constant);
   }
   
   @Override
   public String toString() {
      return "(" + variable.toString() + "=" + constant.toString() + ")";
   }

   public Constant<?> getValue() {
      return constant;
   }

   public Variable getVariable() {
      return variable;
   }

}
