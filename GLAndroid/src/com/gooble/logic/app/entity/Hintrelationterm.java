package com.gooble.logic.app.entity;

public class Hintrelationterm extends Entity{

   private Integer hintrelationid;
   private Integer variablevalueid;
   private Integer hintvariableid;
   public Integer getHintrelationid() {
      return hintrelationid;
   }
   public void setHintrelationid(Integer hintrelationid) {
      this.hintrelationid = hintrelationid;
   }
   public int getVariablevalueid() {
      return variablevalueid;
   }
   public void setVariablevalueid(Integer variablevalueid) {
      this.variablevalueid = variablevalueid;
   }
   public int getHintvariableid() {
      return hintvariableid;
   }
   public void setHintvariableid(Integer hintvariableid) {
      this.hintvariableid = hintvariableid;
   }
}
