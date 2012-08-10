package com.gooble.logic.app.entity;

public class Variable extends Entity{

   private String name;
   private Long puzzleid;
   
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public Long getPuzzleid() {
      return puzzleid;
   }
   public void setPuzzleid(Long puzzleid) {
      this.puzzleid = puzzleid;
   }
   
}
