package com.gooble.logic.puzzle;

public class Property extends HintPart{
   private final String name;
   private final String owner;
   private final Object value;
   public Property(String name, String owner, Object value){
      this.name = name;
      this.owner = owner;
      this.value = value;
   }
   @Override
   public String toString(){
      return name + "Of(" + owner + ", " + value + ")";
   }
}