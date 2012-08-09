package com.gooble.logic.app.entity;

public class Term extends Entity{

   private String value;
   private Integer variableid;
   private Integer variablevalueid;
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
   public Integer getVariablevalueid() {
      return variablevalueid;
   }
   public void setVariablevalueid(Integer variablevalueid) {
      this.variablevalueid = variablevalueid;
   }
   
}
