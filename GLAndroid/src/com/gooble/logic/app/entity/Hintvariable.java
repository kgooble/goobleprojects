package com.gooble.logic.app.entity;

public class Hintvariable extends Entity{

   private String name;
   private Integer hintid;
   private Integer variableid;
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public Integer getHintid() {
      return hintid;
   }
   public void setHintid(Integer hintid) {
      this.hintid = hintid;
   }
   public Integer getVariableid() {
      return variableid;
   }
   public void setVariableid(Integer variableid) {
      this.variableid = variableid;
   }
   
}
