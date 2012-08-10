package com.gooble.logic.app.entity;

public class Variablevalue extends Entity {

   private String value;
   private Long variableid;

   public String getValue() {
      return value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public Long getVariableid() {
      return variableid;
   }

   public void setVariableid(Long variableid) {
      this.variableid = variableid;
   }

}
