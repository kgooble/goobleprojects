package com.gooble.logic.kb.encoding;

import static com.gooble.logic.kb.encoding.TermEncoding.term;
import static com.gooble.logic.kb.encoding.TermEncoding.value;
import static com.gooble.logic.kb.encoding.TermEncoding.variable;
import static com.gooble.logic.kb.encoding.OperatorEncoding.operator;

import java.util.Arrays;

import com.gooble.logic.kb.Replacement;
import com.gooble.logic.kb.Rule;
import com.gooble.logic.kb.solutions.Solution;
import com.gooble.logic.kb.solutions.SolutionSet;
import com.gooble.logic.kb.stmts.Statement;
import com.gooble.logic.kb.terms.Term;

public class KBEncoding {
   public static Solution solution(Replacement... replacements){
      Solution soln = new Solution();
      for (Replacement re : replacements){
         soln.addVariableReplacement(re);
      }
      return soln;
   }
   public static SolutionSet solutions(boolean isQueryTrue, Solution... solutions){
      return new SolutionSet(Arrays.asList(solutions), isQueryTrue);
   }
   
   public static Replacement replacement(String varName, Object value){
      return new Replacement(variable(varName), term(value));
   }
   
   public static Statement statement(String encodedStatement){
      return parseFunction(encodedStatement);
   }
   
   public static Rule rule(String encodedRule){
      encodedRule = encodedRule.replaceAll("\\s", "");
      String[] ruleParts = encodedRule.split("=>");
      String[] antecedentParts = ruleParts[0].split("\\^");
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
      stmtEncoding = stmtEncoding.replaceAll("\\s", "");
      int indexOfOpenBracket = stmtEncoding.indexOf('(');
      int indexOfClosingBracket = stmtEncoding.indexOf(')');
      
      if (indexOfOpenBracket == -1){
         return operator(stmtEncoding);
      } else if (indexOfClosingBracket == -1){
         throw new RuntimeException("Invalid encoding - there was an open bracket but no closing bracket: " + stmtEncoding);
      }
      
      String functionName = stmtEncoding.substring(0, indexOfOpenBracket);
      String[] parameterParts = stmtEncoding.substring(indexOfOpenBracket+1, indexOfClosingBracket).split(",");
      
      Object[] realParams = new Object[parameterParts.length];
      
      int j = 0;
      for (String paramRepr : parameterParts){
         realParams[j] = value(paramRepr);
         j++;
      }
      
      return stmt(functionName, realParams);
   }

   private static Statement stmt(String fn, Object... consts){
      Term<?>[] constants = new Term[consts.length];
      for (int i = 0; i < consts.length; i++){
         constants[i] = term(consts[i]);
      }
      return new Statement(fn, constants);
   }
}
