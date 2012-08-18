package com.gooble.logic.app.entity;

public class Conditionset extends Entity{

   private Long relationid;
   private String name;
   public Long getRelationid() {
      return relationid;
   }
   public void setRelationid(Long relationid) {
      this.relationid = relationid;
   }
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   
}
