package main.kb;

import java.text.NumberFormat;
import java.text.ParseException;

import main.kb.stmts.GreaterThan;
import main.kb.stmts.LessThan;

public class KBEncoding {
   public static Solution solution(Replacement... replacements){
      Solution soln = new Solution();
      for (Replacement re : replacements){
         soln.addVariableReplacement(re);
      }
      return soln;
   }
   
   public static Replacement replacement(String varName, Object value){
      return new Replacement(variable(varName), var(value));
   }
   
   public static Variable variable(String name){
      return new Variable(name);
   }
   
   public static <T> Constant<T> constant(T value){
      return new Constant<T>(value);
   }
   
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
      if (stmtEncoding.indexOf(">") > 0){
         String[] stmtParts = stmtEncoding.split(" > ");
         Term<?> limit = var(parseParameter(stmtParts[1]));
         Term<?> variable = var(stmtParts[0]);
         return new GreaterThan(limit, variable);
      } else if (stmtEncoding.indexOf("<") > 0){
         String[] stmtParts = stmtEncoding.split(" < ");
         Term<?> limit = var(parseParameter(stmtParts[1]));
         Term<?> variable = var(stmtParts[0]);
         return new LessThan(limit, variable);
      }  
      throw new RuntimeException("unexpected operator in encoding: " + stmtEncoding);
   }

   private static Object parseParameter(String paramRepr) {
      try {
         return NumberFormat.getNumberInstance().parse(paramRepr);
      } catch (ParseException e) {
         return paramRepr;
      }
   }
   
   private static Statement stmt(String fn, Object... consts){
      Term<?>[] constants = new Term[consts.length];
      for (int i = 0; i < consts.length; i++){
         constants[i] = var(consts[i]);
      }
      return new Statement(fn, constants);
   }
   
   private static Term<?> var(Object value){
      if (value instanceof String && value.equals(((String)value).toUpperCase())){
         return variable((String) value);
      }else{
         return constant(value);
      }
   }
}
