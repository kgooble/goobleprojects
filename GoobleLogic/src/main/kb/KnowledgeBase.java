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

   public void add(Statement statement) {
      stmts.add(statement);
   }

   public boolean query(Statement statement) {
      for (Statement stmt : stmts){
         if (stmt.match(statement)){
            return true;
         }
      }
      if (statement.isToBeEvaluated()){
         return statement.evaluate();
      }
      return tryToDeriveUsingRules(statement);
   }

   private boolean tryToDeriveUsingRules(Statement statement) {
      List<Solution> solutions = new ArrayList<Solution>();
      for (Rule rule : rules){
         if (rule.consequenceMatches(statement)){
            boolean answer = true;
            for (Statement currStmtToProve : rule.getAntecedents()){
               List<Replacement> replacements = rule.getConsequence().unifyWith(statement);
               solutions.addAll(findSolutions(currStmtToProve.applyReplacements(replacements)));
               boolean subSoln = false;
               Statement workingStatement = currStmtToProve.applyReplacements(replacements);
               for (Solution sol : solutions){
                  workingStatement = currStmtToProve.applyReplacements(replacements).applyReplacements(sol.getReplacements());
                  subSoln |= query(workingStatement);
               }
               if (solutions.isEmpty()){
                  subSoln = query(workingStatement);
               }

               answer &= subSoln;
            }
            return answer;
         }
      }

      return false;
   }

   public void add(Rule rule) {
      rules.add(rule);
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
