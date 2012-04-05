package main.hints;

public class LessThan extends Hint<Number>{
   
   public LessThan(Number value) {
      super(value);
   }

   @Override
   public boolean contradicts(Number value) {
      return value.floatValue() >= this.value.floatValue();
   }

}
