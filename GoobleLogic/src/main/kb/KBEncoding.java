package main.kb;

import java.text.NumberFormat;
import java.text.ParseException;

public class KBEncoding {
   public static Statement stmt(String encodedStatement){
      return parseFunction(encodedStatement);
   }
   
   public static Rule rule(String encodedRule){
      // Rule is of the format "p(X) ^ g(X) => h(X)"
      String[] ruleParts = encodedRule.split(" => ");
      String[] antecedentParts = ruleParts[0].split(" \\^ ");
      String consequent = ruleParts[1];
      
      Statement[] antecedents = new Statement[antecedentParts.length];
      
      int i = 0;
      for (String antPart : antecedentParts){
         antecedents[i] = parseFunction(antPart);
         i++;
      }

      return new Rule(parseFunction(consequent),antecedents);
   }

   private static Statement parseFunction(String stmtEncoding) {
      int indexOfOpenBracket = stmtEncoding.indexOf('(');
      int indexOfClosingBracket = stmtEncoding.indexOf(')');
      
      String functionName = stmtEncoding.substring(0, indexOfOpenBracket);
      String[] parameterParts = stmtEncoding.substring(indexOfOpenBracket+1, indexOfClosingBracket).split(", ");
      
      Object[] realParams = new Object[parameterParts.length];
      
      int j = 0;
      for (String paramRepr : parameterParts){
         Object param;
         try {
            param = NumberFormat.getNumberInstance().parse(paramRepr);
         } catch (ParseException e) {
            param = paramRepr;
         }
         
         realParams[j] = param;
         j++;
      }
      
      return stmt(functionName, realParams);
   }
   
   private static Statement stmt(String fn, Object... consts){
      Constant<?>[] constants = new Constant[consts.length];
      for (int i = 0; i < consts.length; i++){
         constants[i] = var(consts[i]);
      }
      return new Statement(fn, constants);
   }
   
   private static Constant<?> var(Object value){
      if (value instanceof String && value.equals(((String)value).toUpperCase())){
         return new Variable((String) value);
      }else{
         return new Constant<Object>(value);
      }
   }
}
