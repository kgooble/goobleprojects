package com.gooble.logic.app.entity;

public class Hintvariable extends Entity{

   private String name;
   private Long hintid;
   private Long variableid;
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public Long getHintid() {
      return hintid;
   }
   public void setHintid(Long hintid) {
      this.hintid = hintid;
   }
   public Long getVariableid() {
      return variableid;
   }
   public void setVariableid(Long variableid) {
      this.variableid = variableid;
   }
   
}
