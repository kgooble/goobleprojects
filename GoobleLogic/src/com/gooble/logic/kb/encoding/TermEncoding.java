package com.gooble.logic.kb.encoding;

import java.text.NumberFormat;
import java.text.ParseException;

import com.gooble.logic.kb.terms.Constant;
import com.gooble.logic.kb.terms.Term;
import com.gooble.logic.kb.terms.Variable;

public class TermEncoding {
   public static Variable variable(String name){
      return new Variable(name);
   }
   public static <T> Constant<T> constant(T value){
      return new Constant<T>(value);
   }
   public static Object value(String paramRepr) {
      try {
         return NumberFormat.getNumberInstance().parse(paramRepr);
      } catch (ParseException e) {
         return paramRepr;
      }
   }
   public static Term<?> term(Object value){
      if (value instanceof String && value.equals(((String)value).toUpperCase())){
         return variable((String) value);
      }else{
         return constant(value);
      }
   }
}
