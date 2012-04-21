package com.gooble.logic.kb.solutions;

import static com.gooble.logic.Logger.log;

import java.util.List;

public class SolutionSet {

   private List<Solution> solutions;
   private boolean queryTrue;

   public SolutionSet(List<Solution> solutions, boolean queryTrue){
      this.solutions = solutions;
      this.queryTrue = queryTrue;
   }
   
   public List<Solution> list(){
      return solutions;
   }
   
   public boolean isQueryTrue(){
      return queryTrue || !solutions.isEmpty();
   }

   public boolean hasSolutions() {
      return !solutions.isEmpty();
   }

   public void add(SolutionSet statementSolutions) {
      this.solutions.addAll(statementSolutions.list());
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
         log("expanded solution set: " + newSolSet);
         solutions = newSolSet.list();
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
   
   @Override
   public boolean equals(Object obj){
      if (!(obj instanceof SolutionSet))
         return false;
      SolutionSet other = (SolutionSet) obj;
      return other.isQueryTrue() == this.isQueryTrue() && this.solutions.equals(other.solutions);
   }
   
   @Override
   public String toString(){
      StringBuffer solns = new StringBuffer();
      for (Solution soln : solutions)
         solns.append(soln + "\n");
      return "QUERY " + queryTrue + ", SOLUTIONS: " + solns.toString();
   }
   
}
