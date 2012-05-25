package com.gooble.logic.kb.terms;

public interface Term<T> extends Comparable<Term<?>>{
   
   boolean isVariable();
   boolean match(Term<?> other);
   T getValue();
   Term<?> copyWithSuffix(String suffix);

}
