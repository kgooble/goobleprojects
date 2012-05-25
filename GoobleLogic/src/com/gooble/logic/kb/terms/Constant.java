package com.gooble.logic.kb.terms;


public class Constant<T> implements Term<T>{

   private final T value;

   public Constant(T value) {
      this.value = value;
   }

   public boolean match(Term<?> other) {
      return this.getValue().equals(other.getValue());
   }

   @Override
   public String toString(){
      return "CONST:" + value.toString();
   }

   public T getValue() {
      return value;
   }
   
   @Override
   public boolean equals(Object obj) {
      if (!(obj instanceof Constant))
         return false;
      Constant<?> other = (Constant<?>) obj;
      if (this.value instanceof String)
         return other.value.equals(this.value);
      if (this.value instanceof Number && !(other.value instanceof Number))
         return false;
      
      Number num1 = (Number) this.value;
      Number num2 = (Number) other.value;
      return num1.floatValue() == num2.floatValue();
   }
   @Override
   public int hashCode(){
      return value.hashCode();
   }

   public boolean isVariable() {
      return false;
   }

   @Override
   public Term<?> copyWithSuffix(String suffix) {
      return this;
   }

   @Override
   public int compareTo(Term<?> o) {
      if (o instanceof Variable)
         return 1;
      Constant<?> other = (Constant<?>) o;
      if (this.value instanceof String && other.value instanceof String)
         return ((String)this.value).compareTo(((String)other.value));
      if (this.value instanceof Number && !(other.value instanceof Number))
         return 1;
      if (!(this.value instanceof Number) && (other.value instanceof Number))
         return -1;
      
      Number num1 = (Number) this.value;
      Number num2 = (Number) other.value;
         
      if (num1.floatValue() > num2.floatValue())
         return 1;
      if (num1.floatValue() < num2.floatValue())
         return -1;
      return 0;
   }

}
