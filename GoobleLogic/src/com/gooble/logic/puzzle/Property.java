package com.gooble.logic.puzzle;

public class Property extends HintPart{
   private final String name;
   private final String owner;
   private final String value;
   public Property(String name, String owner, String value){
      this.name = name;
      this.owner = owner;
      this.value = value;
   }
   @Override
   public String toString(){
      return name + "Of(" + owner + ", " + value + ")";
   }
}