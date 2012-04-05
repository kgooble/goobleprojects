package main;

import java.util.Arrays;
import java.util.List;

import main.hints.Hint;

public class Variable<T> {

   private final T[] values;

   public Variable(T... values) {
      this.values = values;
   }

   public Deducer<T> givenHints(Hint<T>... hints) {
      return new Deducer<T>(this, hints);
   }

   public List<T> getValues() {
      return Arrays.asList(values);
   }

}
