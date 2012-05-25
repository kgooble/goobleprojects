package com.gooble.logic.kb.terms;


public class Variable implements Term<String>, Comparable<Term<?>> {

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
   public int hashCode(){
      return name.hashCode();
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

   @Override
   public Term<?> copyWithSuffix(String suffix) {
      return new Variable(name + suffix);
   }

   @Override
   public int compareTo(Term<?> other) {
      if (!(other instanceof Variable))
         return -1;
      Variable otherVar = (Variable) other;
      return this.name.compareTo(otherVar.name);
   }

}
