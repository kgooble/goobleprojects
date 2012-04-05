package main.hints;

public class GreaterThan extends Hint<Number> {

   public GreaterThan(Number value) {
      super(value);
   }

   @Override
   public boolean contradicts(Number value) {
      return value.floatValue() <= this.value.floatValue();
   }

}
