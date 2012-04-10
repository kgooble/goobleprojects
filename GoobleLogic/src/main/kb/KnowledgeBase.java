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

   public void add(Rule rule) {
      rules.add(rule);
   }

   public boolean query(Statement statement) {
      return findSolutions(statement).isQueryTrue();
   }

   public SolutionSet findSolutions(Statement statement) {
      List<Solution> solns = new ArrayList<Solution>();
      if (matchesStatement(statement, solns))
         return new SolutionSet(solns, true);
      if (statement.isToBeEvaluated()){
         return new SolutionSet(solns, statement.evaluate());
      }
      return collectSolutionsUsingRules(statement);
   }

   private SolutionSet collectSolutionsUsingRules(Statement statement) {
      SolutionSet solution = new SolutionSet(new ArrayList<Solution>(), false);
      boolean atLeastOneRuleSucceeded = false;
      for (Rule rule : rules){
         if (rule.consequenceMatches(statement)){
            SolutionSet subSolSet = collectSolutionsForRule(statement, rule);
            atLeastOneRuleSucceeded |= subSolSet.isQueryTrue();
            solution.add(subSolSet);
         }
      }
      solution.setSucceeded(atLeastOneRuleSucceeded);
      return solution;
   }

   private SolutionSet collectSolutionsForRule(Statement statement, Rule rule) {
      SolutionSet ruleSolutionSet = new SolutionSet(new ArrayList<Solution>(), true);
      for (Statement currStmtToProve : rule.getAntecedents()){
         List<Replacement> replacements = rule.getConsequence().unifyWith(statement);
         if (ruleSolutionSet.hasSolutions()){
            expandSolutionSet(ruleSolutionSet, currStmtToProve, replacements);
         }else{
            ruleSolutionSet.add(findSolutions(currStmtToProve.applyReplacements(replacements)));
         }
      }
      return ruleSolutionSet;
   }

   private void expandSolutionSet(SolutionSet ruleSolutionSet, Statement statementToProve, List<Replacement> replacements) {
      SolutionSet expandedRuleSolutionSet = new SolutionSet(new ArrayList<Solution>(), false);
      for (int i = ruleSolutionSet.size() - 1; i >= 0; i--){
         Solution currentSolution = ruleSolutionSet.get(i);
         Statement fullyUnifiedStatement = statementToProve.applyReplacements(replacements).applyReplacements(currentSolution.getReplacements());
         SolutionSet statementSolutions = findSolutions(fullyUnifiedStatement);

         if (!statementSolutions.isQueryTrue()){
            ruleSolutionSet.remove(i);
            i--;
         }else if (statementSolutions.hasSolutions()){
            for (Solution statementSol : statementSolutions.getSolutions()){
               expandedRuleSolutionSet.add(currentSolution.mergeWith(statementSol));
            }
         }
      }
      ruleSolutionSet.replaceSolutionsIfNotEmpty(expandedRuleSolutionSet);
   }

   private boolean matchesStatement(Statement statement, List<Solution> solns) {
      boolean matchesStatement = false;
      for (Statement stmt : stmts){
         if (stmt.match(statement)){
            List<Replacement> replacements = statement.unifyWith(stmt);
            matchesStatement |= (statement.containsVariables() != replacements.isEmpty());
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

   @Override
   public String toString(){
      return "STATEMENTS: " + stmts.toString() + "\n" + "RULES: " + rules.toString();
   }
}
