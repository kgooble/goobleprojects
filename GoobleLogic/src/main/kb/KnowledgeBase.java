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
      return findSolutions(statement).isQueryTrue();
   }

   public void add(Rule rule) {
      rules.add(rule);
   }

   @Override
   public String toString(){
      return "STATEMENTS: " + stmts.toString() + "\n" + "RULES: " + rules.toString();
   }

   public SolutionSet findSolutions(Statement statement) {
      List<Solution> solns = new ArrayList<Solution>();
      
      boolean queryWithNoVariablesTrue = checkIfMatchesStatement(statement, solns);
      SolutionSet solution = new SolutionSet(solns, queryWithNoVariablesTrue);
      if (queryWithNoVariablesTrue)
         return solution;
      if (statement.isToBeEvaluated()){
         return new SolutionSet(solns, statement.evaluate());
      }
      for (Rule rule : rules){
         if (rule.consequenceMatches(statement)){
            SolutionSet subSolSet = new SolutionSet(new ArrayList<Solution>(), true);
            
            for (Statement currStmtToProve : rule.getAntecedents()){
               List<Replacement> replacements = rule.getConsequence().unifyWith(statement);
               if (!subSolSet.hasSolutions()){
                  SolutionSet statementSolutions = findSolutions(currStmtToProve.applyReplacements(replacements));
                  subSolSet.add(statementSolutions);
               }else{
                  SolutionSet newSubSolSet = new SolutionSet(new ArrayList<Solution>(), false);
                  
                  for (int i = subSolSet.size() - 1; i >= 0; i--){
                     Solution currentSolution = subSolSet.get(i);
                     Statement fullyUnifiedStatement = currStmtToProve.applyReplacements(replacements).applyReplacements(currentSolution.getReplacements());
                     SolutionSet statementSolutions = findSolutions(fullyUnifiedStatement);
                     
                     if (!statementSolutions.isQueryTrue()){
                        subSolSet.remove(i);
                        i--;
                     }else if (statementSolutions.hasSolutions()){
                        for (Solution statementSol : statementSolutions.getSolutions()){
                           newSubSolSet.add(currentSolution.mergeWith(statementSol));
                        }
                     }
                  }
                  subSolSet.replaceSolutionsIfNotEmpty(newSubSolSet);
               }
            }
            
            queryWithNoVariablesTrue |= subSolSet.isQueryTrue();
            solution.add(subSolSet);
         }
      }

      solution.setSucceeded(queryWithNoVariablesTrue);
      return solution;
   }

   private boolean checkIfMatchesStatement(Statement statement, List<Solution> solns) {
      boolean matchesStatement = false;
      for (Statement stmt : stmts){
         if (stmt.match(statement)){
            matchesStatement = true;
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
      return matchesStatement;
   }

}
