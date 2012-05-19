package com.gooble.logic.puzzle;

public class Result{

   private final boolean puzzleSolvable;

   public Result(boolean puzzleSolvable) {
      this.puzzleSolvable = puzzleSolvable;
   }
   
   public boolean isPuzzleSolvable() {
      return puzzleSolvable;
   }
   
}