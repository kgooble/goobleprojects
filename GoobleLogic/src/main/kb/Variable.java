package main.kb;


public class Variable implements Term<String> {

   private final String name;

   public Variable(String name) {
      this.name = name;
   }
   
   @Override
   public boolean equals(Object obj){
      if (!(obj instanceof Variable))
         return false;
      Variable other = (Variable) obj;
      return this.getValue().equals(other.getValue());
   }
   
   @Override
   public boolean isVariable() {
      return true;
   }

   @Override
   public boolean match(Term<?> other) {
      return !(other instanceof Variable) || ((Variable) other).getValue().equals(getValue());
   }

   @Override
   public String getValue() {
      return name;
   }

}
