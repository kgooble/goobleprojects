package com.gooble.logic.app.entity;

public class Variable extends Entity{

   private String name;
   private Integer puzzleid;
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public Integer getPuzzleid() {
      return puzzleid;
   }
   public void setPuzzleid(Integer puzzleid) {
      this.puzzleid = puzzleid;
   }
   
}
