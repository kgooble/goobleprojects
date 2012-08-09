package com.gooble.logic.app.entity;

public class Variablevalue extends Entity{

   private String value;
   private Integer variableid;
   public String getValue() {
      return value;
   }
   public void setValue(String value) {
      this.value = value;
   }
   public Integer getVariableid() {
      return variableid;
   }
   public void setVariableid(Integer variableid) {
      this.variableid = variableid;
   }
   
}
