package main.kb;

public class Constant<T> {

   private final T value;

   public Constant(T value) {
      this.value = value;
   }

   public boolean match(Constant<?> other) {
      return this.value == other.value;
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
      return other.value == this.value;
   }

   public boolean isVariable() {
      return false;
   }


}
