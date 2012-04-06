package main.hints;

abstract public class Hint<T> {

   protected final T value;

   public Hint(T value){
      this.value = value;
   }

   abstract public boolean contradicts(T value);

   public static <X> Not<X> Not(Hint<X> hint){
      return new Not<X>(hint);
   }

   public static <X> Equals<X> Equals(X value){
      return new Equals<X>(value);
   }

   public static LessThan LessThan(Number value){
      return new LessThan(value);
   }

   public static GreaterThan GreaterThan(Number value){
      return new GreaterThan(value);
   }

}
