package main.kb;

import java.util.List;

public class SolutionSet {

   private List<Solution> solutions;
   private boolean queryTrue;

   public SolutionSet(List<Solution> solutions, boolean queryTrue){
      this.solutions = solutions;
      this.queryTrue = queryTrue;
   }
   
   public List<Solution> getSolutions(){
      return solutions;
   }
   
   public boolean isQueryTrue(){
      return queryTrue || !solutions.isEmpty();
   }

   public boolean hasSolutions() {
      return !solutions.isEmpty();
   }
   
   @Override
   public String toString(){
      return "QUERY " + queryTrue + ", SOLUTIONS: " + solutions.toString();
   }

   public void add(SolutionSet statementSolutions) {
      this.solutions.addAll(statementSolutions.getSolutions());
      queryTrue &= statementSolutions.isQueryTrue();
   }

   public int size() {
      return solutions.size();
   }

   public Solution get(int i) {
      return solutions.get(i);
   }

   public void remove(int i) {
      solutions.remove(i);
   }

   public void replaceSolutionsIfNotEmpty(SolutionSet newSolSet) {
      if (newSolSet.hasSolutions()){
         solutions = newSolSet.getSolutions();
      }
      queryTrue &= newSolSet.isQueryTrue();
   }

   public void setSucceeded(boolean queryWithNoVariablesTrue) {
      queryTrue = queryWithNoVariablesTrue || !solutions.isEmpty();
   }

   public void add(Solution solution) {
      solutions.add(solution);
      queryTrue = true;
   }
   
}
