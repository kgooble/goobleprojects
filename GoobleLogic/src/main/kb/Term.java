package main.kb;

public interface Term<T> {
   
   boolean isVariable();
   boolean match(Term<?> other);
   T getValue();

}
