package main.hints;

public class Equals<T> extends Hint<T>{

   public Equals(T value) {
      super(value);
   }

   @Override
   public boolean contradicts(T value) {
      return !this.value.equals(value);
   }

}
