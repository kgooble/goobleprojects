package main.kb;

import java.text.NumberFormat;
import java.text.ParseException;

import main.kb.stmts.GreaterThan;

public class KBEncoding {
   public static Statement statement(String encodedStatement){
      return parseFunction(encodedStatement);
   }
   
   public static Rule rule(String encodedRule){
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
      
      if (indexOfOpenBracket == -1){
         return parseOperator(stmtEncoding);
      } else if (indexOfClosingBracket == -1){
         throw new RuntimeException("Invalid encoding - there was an open bracket but no closing bracket: " + stmtEncoding);
      }
      
      String functionName = stmtEncoding.substring(0, indexOfOpenBracket);
      String[] parameterParts = stmtEncoding.substring(indexOfOpenBracket+1, indexOfClosingBracket).split(", ");
      
      Object[] realParams = new Object[parameterParts.length];
      
      int j = 0;
      for (String paramRepr : parameterParts){
         realParams[j] = parseParameter(paramRepr);
         j++;
      }
      
      return stmt(functionName, realParams);
   }

   private static Statement parseOperator(String stmtEncoding) {
      String[] stmtParts = stmtEncoding.split(" > ");
      Constant<?> limit = var(parseParameter(stmtParts[1]));
      Constant<?> variable = var(stmtParts[0]);
      return new GreaterThan((Constant<Number>) limit, variable);
   }

   private static Object parseParameter(String paramRepr) {
      try {
         return NumberFormat.getNumberInstance().parse(paramRepr);
      } catch (ParseException e) {
         return paramRepr;
      }
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
