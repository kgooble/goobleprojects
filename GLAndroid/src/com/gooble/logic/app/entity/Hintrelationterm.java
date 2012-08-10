package com.gooble.logic.app.entity;

public class Hintrelationterm extends Entity{

   private Long hintrelationid;
   private Long variablevalueid;
   private Long hintvariableid;
   public Long getHintrelationid() {
      return hintrelationid;
   }
   public void setHintrelationid(Long hintrelationid) {
      this.hintrelationid = hintrelationid;
   }
   public Long getVariablevalueid() {
      return variablevalueid;
   }
   public void setVariablevalueid(Long variablevalueid) {
      this.variablevalueid = variablevalueid;
   }
   public Long getHintvariableid() {
      return hintvariableid;
   }
   public void setHintvariableid(Long hintvariableid) {
      this.hintvariableid = hintvariableid;
   }
}
