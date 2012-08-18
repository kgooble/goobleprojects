package com.gooble.logic.app.entity;

public class Puzzle extends Entity{

   private String name;
   private Long mainvariableid;
   
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public Long getMainvariableid() {
      return mainvariableid;
   }
   public void setMainvariableid(Long mainvariableid) {
      this.mainvariableid = mainvariableid;
   }
}
