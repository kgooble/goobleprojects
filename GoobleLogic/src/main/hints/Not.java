package main.hints;

public class Not<T> extends Hint<T> {

   private final Hint<T> hint;
   
   public Not(Hint<T> hint) {
      super(null);
      this.hint = hint;
   }

   @Override
   public boolean contradicts(T value) {
      return !hint.contradicts(value);
   }

}
