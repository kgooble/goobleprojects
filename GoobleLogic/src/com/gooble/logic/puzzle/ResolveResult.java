package com.gooble.logic.puzzle;

public class ResolveResult{

   private final boolean consistent;

   public ResolveResult(boolean consistent) {
      this.consistent = consistent;
   }

   public boolean isPuzzleConsistent() {
      return consistent;
   }
   
}