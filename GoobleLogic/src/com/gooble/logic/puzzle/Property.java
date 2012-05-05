package com.gooble.logic.puzzle;


public class Property implements HintPart{
   private final String name;
   private final String owner;
   private final String value;
   public Property(String name, String owner, String value){
      this.name = name;
      this.owner = owner;
      this.value = value;
   }
   @Override
   public boolean equals(Object obj){
      if (!(obj instanceof Property))
         return false;
      Property other = (Property) obj;
      return this.name.equals(other.name) && this.owner.equals(other.owner) && this.value.equals(other.value);
   }
   @Override
   public String toString(){
      return "Property: " + name + " of " + owner + " is " + value;
   }
}