package com.gooble.logic.app.entity;

public class Term extends Entity{

   private String value;
   private int variableid;
   private int variablevalueid;
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
   public int getVariablevalueid() {
      return variablevalueid;
   }
   public void setVariablevalueid(int variablevalueid) {
      this.variablevalueid = variablevalueid;
   }
   
}
