package main.kb;

import java.util.ArrayList;
import java.util.List;

public class Solution {
   private List<Replacement> replacements = new ArrayList<Replacement>();
   
   public void addVariableReplacement(Replacement re) {
      replacements.add(re);
   }
   
   @Override
   public String toString() {
      return "SOLUTION: " + replacements.toString();
   }
   
   @Override
   public boolean equals(Object obj){
      if (!(obj instanceof Solution))
         return false;
      return replacements.equals(((Solution)obj).replacements);
   }

   public List<Replacement> getReplacements() {
      return replacements;
   }

   public void addReplacements(List<Replacement> replacements) {
      this.replacements.addAll(replacements);
   }

   public Solution mergeWith(Solution other) {
      Solution mergedSol = new Solution();
      mergedSol.addReplacements(this.getReplacements());
      mergedSol.addReplacements(other.getReplacements());
      return mergedSol;
   }

}
