package main.kb;


public class Variable extends Constant<String> {

   public Variable(String name) {
      super(name);
   }
   
   @Override
   public boolean match(Constant<?> other){
      return !(other instanceof Variable) || ((Variable) other).getValue().equals(getValue());
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

}
