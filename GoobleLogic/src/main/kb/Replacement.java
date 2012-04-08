package main.kb;

public class Replacement {

   private final Term<?> replacement;
   private final Variable variable;

   public Replacement(Variable variable, Term<?> replacement) {
      this.variable = variable;
      this.replacement = replacement;
   }
   
   @Override
   public boolean equals(Object obj){
      if (!(obj instanceof Replacement))
         return false;
      Replacement other = (Replacement) obj;
      return other.variable.equals(this.variable) && other.replacement.equals(this.replacement);
   }
   
   @Override
   public String toString() {
      return "(" + variable.toString() + "=" + replacement.toString() + ")";
   }

   public Term<?> getValue() {
      return replacement;
   }

   public Variable getVariable() {
      return variable;
   }

}
