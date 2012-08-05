package com.gooble.logic.app.entity;

public class Variable extends Entity{

   private String name;
   private int puzzleid;
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public int getPuzzleid() {
      return puzzleid;
   }
   public void setPuzzleid(int puzzleid) {
      this.puzzleid = puzzleid;
   }
   
}
