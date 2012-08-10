package com.gooble.logic.app.entity;

public class Term extends Entity{

   private String value;
   private Long variableid;
   private Long variablevalueid;
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
   public Long getVariablevalueid() {
      return variablevalueid;
   }
   public void setVariablevalueid(Long variablevalueid) {
      this.variablevalueid = variablevalueid;
   }
   
}
