package com.gooble.logic.kb.encoding;

import static com.gooble.logic.kb.encoding.TermEncoding.term;
import static com.gooble.logic.kb.encoding.TermEncoding.value;

import java.util.HashMap;
import java.util.Map;

import com.gooble.logic.kb.Equals;
import com.gooble.logic.kb.stmts.GreaterThan;
import com.gooble.logic.kb.stmts.LessThan;
import com.gooble.logic.kb.stmts.Operator;
import com.gooble.logic.kb.terms.Term;

public class OperatorEncoding {

   private final static Map<String, Class<? extends Operator>> OPERATORS;
   static{
      OPERATORS = new HashMap<String, Class<? extends Operator>>();
      OPERATORS.put(">", GreaterThan.class);
      OPERATORS.put("<", LessThan.class);
      OPERATORS.put("=", Equals.class);
   }
   
   public static Operator operator(String stmtEncoding) {
      stmtEncoding = stmtEncoding.replaceAll("\\s", "");
      for (Map.Entry<String, Class<? extends Operator>> entry : OPERATORS.entrySet()){
         if (stmtEncoding.indexOf(entry.getKey()) > 0){
            String[] stmtParts = stmtEncoding.split(entry.getKey());
            Term<?> limit = term(value(stmtParts[1]));
            Term<?> variable = term(value(stmtParts[0]));
            try {
               return entry.getValue().getConstructor(Term.class, Term.class).newInstance(limit, variable);
            } catch (Exception e) {
               throw new RuntimeException("error while parsing: " + stmtEncoding, e);
            }
         }
      }
      throw new RuntimeException("unexpected operator in encoding: " + stmtEncoding);
   }
}
