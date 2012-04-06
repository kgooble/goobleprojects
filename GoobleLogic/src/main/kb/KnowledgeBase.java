package main.kb;

import java.util.ArrayList;
import java.util.List;

public class KnowledgeBase {

   private List<Statement> stmts;
   private List<Rule> rules;

   public KnowledgeBase(){
      stmts = new ArrayList<Statement>();
      rules = new ArrayList<Rule>();
   }
   
   public void addStatement(Statement statement) {
      stmts.add(statement);
   }

   public boolean query(Statement statement) {
      for (Statement stmt : stmts){
         if (stmt.match(statement)){
            return true;
         }
      }
      return tryToDeriveUsingRules(statement);
   }

   private boolean tryToDeriveUsingRules(Statement statement) {
      for (Rule rule : rules){
         if (rule.consequenceMatches(statement)){
            boolean answer = true;
            for (Statement proveFirst : rule.getAntecedents()){
               answer &= query(proveFirst.replaceVariableWithValue(statement.getValue()));
            }
            return answer;
         }
      }
      
      return false;
   }

   public void addRule(Statement consequence, Statement... antecedents) {
      rules.add(new Rule(consequence, antecedents));
   }
   
   @Override
   public String toString(){
      return "STATEMENTS: " + stmts.toString() + "\n" + "RULES: " + rules.toString();
   }

   public List<Solution> findSolutions(Statement statement) {
      List<Solution> solns = new ArrayList<Solution>();
      
      for (Statement stmt : stmts){
         if (stmt.match(statement)){
            List<Replacement> replacements = statement.unifyWith(stmt);
            if (replacements.isEmpty())
               continue;
            Solution soln = new Solution();
            for (Replacement re : replacements){
               soln.addVariableReplacement(re);
            }
            solns.add(soln);
         }
      }
      
      return solns;
   }

}
