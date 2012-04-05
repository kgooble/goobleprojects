package main;

import java.util.ArrayList;
import java.util.List;

import main.hints.Hint;

public class Deducer<T> {

   private final Hint<T>[] hints;
   private final Variable<T> var;

   public Deducer(Variable<T> var, Hint<T>... hints) {
      this.var = var;
      this.hints = hints;
   }

   public List<T> listPossibleValues() {
      List<T> list = new ArrayList<T>();
      list.addAll(var.getValues());
      removeContradictoryValues(list);
      return list;
   }

   private void removeContradictoryValues(List<T> values) {
      for (int i = values.size() - 1; i >= 0; i--){
         for (Hint<T> h : hints){
            if (h.contradicts(values.get(i))){
               values.remove(i);
               break;
            }
         }
      }
   }

}
