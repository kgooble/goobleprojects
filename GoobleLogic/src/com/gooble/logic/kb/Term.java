package com.gooble.logic.kb;

public interface Term<T> {
   
   boolean isVariable();
   boolean match(Term<?> other);
   T getValue();
   Term<?> copyWithSuffix(String suffix);

}
