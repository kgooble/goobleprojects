package com.gooble.logic.puzzle;


public class Relation implements HintPart{
   private final String name;
   private final String term1;
   private final String term2;
   public Relation(String name, String term1, String term2) {
      this.name = name;
      this.term1 = term1;
      this.term2 = term2;
   }
   @Override
   public boolean equals(Object obj){
      if (!(obj instanceof Relation))
         return false;
      Relation other = (Relation) obj;
      return this.name.equals(other.name) && this.term1.equals(other.term1) && this.term2.equals(other.term2);
   }
   @Override
   public String toString(){
      return "Relation: " + term1 + " is " + name + " " + term2;
   }
}