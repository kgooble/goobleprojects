package com.gooble.logic.puzzle;

public class Relation extends HintPart{
   private final String name;
   private final String term1;
   private final String term2;
   public Relation(String name, String term1, String term2) {
      this.name = name;
      this.term1 = term1;
      this.term2 = term2;
   }
   @Override
   public String toString(){
      return name + "(" + term1 + ", " + term2 + ")";
   }
}