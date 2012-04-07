package main.kb;

public class Constant<T> {

   private final T value;

   public Constant(T value) {
      this.value = value;
   }

   public boolean match(Constant<?> other) {
      return this.value.equals(other.value);
   }
   

   @Override
   public String toString(){
      return value.toString();
   }

   public T getValue() {
      return value;
   }
   
   @Override
   public boolean equals(Object obj) {
      if (!(obj instanceof Constant) || (obj instanceof Variable))
         return false;
      Constant<T> other = (Constant<T>) obj;
      if (this.value instanceof String)
         return other.value.equals(this.value);
      
      if (this.value instanceof Number && !(other.value instanceof Number))
         return false;
      
      Number num1 = (Number) this.value;
      Number num2 = (Number) other.value;
      return num1.floatValue() == num2.floatValue();
   }

   public boolean isVariable() {
      return false;
   }


}
