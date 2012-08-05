package com.gooble.logic.app.entity;

public class Hintvariable extends Entity{

   private String name;
   private int hintid;
   private int variableid;
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public int getHintid() {
      return hintid;
   }
   public void setHintid(int hintid) {
      this.hintid = hintid;
   }
   public int getVariableid() {
      return variableid;
   }
   public void setVariableid(int variableid) {
      this.variableid = variableid;
   }
   
}
