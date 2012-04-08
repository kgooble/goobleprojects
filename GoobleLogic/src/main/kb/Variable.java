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
      return true;
   }

   @Override
   public String getValue() {
      return name;
   }
   
   @Override
   public String toString(){
      return "VAR:" + name.toString();
   }

}
