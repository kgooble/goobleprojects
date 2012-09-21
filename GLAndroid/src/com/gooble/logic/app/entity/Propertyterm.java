package com.gooble.logic.app.entity;

public class Propertyterm extends Entity{
   private Long propertyid;
   private Long hintvariableid;
   private Long variablevalueid;
   public Long getPropertyid() {
      return propertyid;
   }
   public void setPropertyid(Long propertyid) {
      this.propertyid = propertyid;
   }
   public Long getHintvariableid() {
      return hintvariableid;
   }
   public void setHintvariableid(Long hintvariableid) {
      this.hintvariableid = hintvariableid;
   }
   public Long getVariablevalueid() {
      return variablevalueid;
   }
   public void setVariablevalueid(Long variablevalueid) {
      this.variablevalueid = variablevalueid;
   }
}
