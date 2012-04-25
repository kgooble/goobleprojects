package com.gooble.logic.kb;

import static com.gooble.logic.Logger.log;

import java.util.ArrayList;
import java.util.List;

import com.gooble.logic.kb.solutions.Solution;
import com.gooble.logic.kb.solutions.Normalize;
import com.gooble.logic.kb.solutions.SolutionSet;
import com.gooble.logic.kb.stmts.Statement;

public class KnowledgeBase implements KnowledgeBaseFacade{

   private List<Statement> stmts;
   private List<Rule> rules;

   public KnowledgeBase(){
      stmts = new ArrayList<Statement>();
      rules = new ArrayList<Rule>();
   }

   public void add(Statement statement) {
      log("Adding statement: <<" + statement + ">>");
      stmts.add(statement);
   }

   public void add(Rule rule) {
      log("Adding rule: <<" + rule + ">>");
      rules.add(rule);
   }

   public boolean query(Statement statement) {
      return findSolutions(statement).isQueryTrue();
   }

   public SolutionSet findSolutions(Statement statement) {
      log("---------");
      log("Begin find solutions on statement: <<" + statement + ">>");
      log();
      List<Solution> solns = new ArrayList<Solution>();
      if (matchesStatement(statement, solns))
         return new SolutionSet(solns, true);
      if (statement.isToBeEvaluated()){
         boolean eval = statement.evaluate();
         log("evaluated to " + eval);
         return new SolutionSet(solns, eval);
      }
      return collectSolutionsUsingRules(statement);
   }

   private SolutionSet collectSolutionsUsingRules(Statement statement) {
      SolutionSet solution = new SolutionSet(new ArrayList<Solution>(), false);
      boolean atLeastOneRuleSucceeded = false;
      log("", "Applying rules for <<" + statement + ">>", "");
      for (Rule rule : rules){
         if (rule.consequenceMatches(statement)){
            List<Replacement> originalReplacements = statement.unifyWith(rule.getConsequence());
            Statement workingStatement = statement.applyReplacements(originalReplacements);
            SolutionSet subSolSet = collectSolutionsForRule(workingStatement, rule);
            atLeastOneRuleSucceeded |= subSolSet.isQueryTrue();
            SolutionSet normalizedSoln = new Normalize(originalReplacements).solutions(subSolSet);
            solution.add(normalizedSoln);
         }
      }
      log("at least one rule succeeded? " + atLeastOneRuleSucceeded);
      solution.setSucceeded(atLeastOneRuleSucceeded);
      log("final solution for statement <<" + statement +">> : <<" + solution + ">>", "");
      return solution;
   }

   private SolutionSet collectSolutionsForRule(Statement statement, Rule rule) {
      SolutionSet ruleSolutionSet = new SolutionSet(new ArrayList<Solution>(), true);
      for (Statement currStmtToProve : rule.getAntecedents()){
         log("proving antecedent : <<" + currStmtToProve + ">>");
         log();
         List<Replacement> replacements = rule.getConsequence().unifyWith(statement);
         if (ruleSolutionSet.hasSolutions()){
            expandSolutionSet(ruleSolutionSet, currStmtToProve, replacements);
         }else{
            log("Adding initial solutions");
            ruleSolutionSet.add(findSolutions(currStmtToProve.applyReplacements(replacements)));
         }
      }
      return ruleSolutionSet;
   }

   private void expandSolutionSet(SolutionSet ruleSolutionSet, Statement statementToProve, List<Replacement> replacements) {
      log("Expanding solution set <<" + ruleSolutionSet + ">> with replacements <<" + replacements + ">>");
      SolutionSet expandedRuleSolutionSet = new SolutionSet(new ArrayList<Solution>(), false);
      for (int i = ruleSolutionSet.size() - 1; i >= 0; i--){
         Solution currentSolution = ruleSolutionSet.get(i);
         log("current statement to prove while expanding: <<" + statementToProve + ">>");
         Statement fullyUnifiedStatement = statementToProve.applyReplacements(replacements).applyReplacements(currentSolution.getReplacements());
         log("Working on solution: <<" + currentSolution + ">>");
         log("Fully unified: " + fullyUnifiedStatement);
         SolutionSet statementSolutions = findSolutions(fullyUnifiedStatement);

         if (!statementSolutions.isQueryTrue()){
            ruleSolutionSet.remove(i);
         }else if (statementSolutions.hasSolutions()){
            for (Solution statementSol : statementSolutions.list()){
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
            log("statement : <<" + statement + ">> matched <<" + stmt + ">>");
            List<Replacement> replacements = statement.unifyWith(stmt);
            matchesStatement |= (statement.containsVariables() != replacements.isEmpty());
            if (replacements.isEmpty())
               continue;
            Solution soln = new Solution();
            for (Replacement re : replacements){
               soln.addVariableReplacement(re);
            }
            solns.add(soln);
            log("added solution for <<" + statement + ">> : solution: <<" + soln + ">>");
         }
      }
      log("Matches statement? " + matchesStatement);
      return matchesStatement;
   }

   @Override
   public String toString(){
      return "STATEMENTS: " + stmts.toString() + "\n" + "RULES: " + rules.toString();
   }
}
