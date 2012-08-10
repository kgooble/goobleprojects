package com.gooble.logic.app.entity;

public class Propertyterm extends Entity{
   private Long propertyid;
   private Long hintvariableid;
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
}
