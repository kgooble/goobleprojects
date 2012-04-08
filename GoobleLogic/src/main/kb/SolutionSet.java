package main.kb;

import java.util.List;

public class SolutionSet {

   private final boolean queryTrue;
   private final List<Solution> solutions;

   public SolutionSet(List<Solution> solutions, boolean queryTrue){
      this.solutions = solutions;
      this.queryTrue = queryTrue;
   }
   
   public List<Solution> getSolutions(){
      return solutions;
   }
   
   public boolean isQueryTrue(){
      return queryTrue;
   }

   public boolean hasSolutions() {
      return !solutions.isEmpty();
   }
   
   @Override
   public String toString(){
      return "QUERY " + queryTrue + ", SOLUTIONS: " + solutions.toString();
   }
   
}
