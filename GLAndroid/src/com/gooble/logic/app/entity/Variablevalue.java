package com.gooble.logic.app.entity;

public class Variablevalue extends Entity{

   private String value;
   private int variableid;
   public String getValue() {
      return value;
   }
   public void setValue(String value) {
      this.value = value;
   }
   public int getVariableid() {
      return variableid;
   }
   public void setVariableid(int variableid) {
      this.variableid = variableid;
   }
   
}
