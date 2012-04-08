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
      if (statement.isToBeEvaluated()){
         return new SolutionSet(solns, statement.evaluate());
      }
      for (Rule rule : rules){
         if (rule.consequenceMatches(statement)){
            List<Solution> subSolns = new ArrayList<Solution>();
            boolean allStatementsTrue = true;
            for (Statement currStmtToProve : rule.getAntecedents()){
               List<Replacement> replacements = rule.getConsequence().unifyWith(statement);
               if (subSolns.isEmpty()){
                  SolutionSet statementSolutions = findSolutions(currStmtToProve.applyReplacements(replacements));
                  subSolns.addAll(statementSolutions.getSolutions());
                  allStatementsTrue &= statementSolutions.isQueryTrue();
               }else{
                  List<Solution> newSubSolns = new ArrayList<Solution>();
                  boolean atLeastOneSolutionResultedInTrueAnswer = false;
                  for (int i = subSolns.size() - 1; i >= 0; i--){
                     Solution currentSolution = subSolns.get(i);
                     
                     Statement fullyUnifiedStatement = currStmtToProve.applyReplacements(replacements).applyReplacements(currentSolution.getReplacements());
                     SolutionSet statementSolutions = findSolutions(fullyUnifiedStatement);
                     atLeastOneSolutionResultedInTrueAnswer |= statementSolutions.isQueryTrue();
                     if (!statementSolutions.isQueryTrue()){
                        subSolns.remove(i);
                        i--;
                     }else if (statementSolutions.hasSolutions()){
                        for (Solution statementSol : statementSolutions.getSolutions()){
                           newSubSolns.add(currentSolution.mergeWith(statementSol));
                        }
                     }
                  }
                  allStatementsTrue &= atLeastOneSolutionResultedInTrueAnswer;
                  if (!newSubSolns.isEmpty()){
                     subSolns = newSubSolns;
                  }
               }
            }
            matchesStatement |= allStatementsTrue;
            solns.addAll(subSolns);
         }
      }

      return new SolutionSet(solns, matchesStatement||!solns.isEmpty());
   }

}
